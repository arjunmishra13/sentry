/*
  Licensed to the Apache Software Foundation (ASF) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The ASF licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at
  <p>
  http://www.apache.org/licenses/LICENSE-2.0
  <p>
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package org.apache.sentry.service.thrift;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Timer;
import com.codahale.metrics.Timer.Context;
import com.google.common.annotations.VisibleForTesting;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.CurrentNotificationEventId;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.NotificationEvent;
import org.apache.hadoop.hive.metastore.api.NotificationEventResponse;
import org.apache.hadoop.hive.metastore.messaging.MessageDeserializer;
import org.apache.sentry.binding.metastore.messaging.json.SentryJSONMessageDeserializer;
import org.apache.sentry.core.common.utils.SentryConstants;
import org.apache.sentry.provider.db.service.persistent.PathsImage;
import org.apache.sentry.api.service.thrift.SentryMetrics;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * Wrapper class for <Code>HiveMetaStoreClient</Code>
 *
 * <p>Abstracts communication with HMS and exposes APi's to connect/disconnect to HMS and to
 * request HMS snapshots and also for new notifications.
 */
public class SentryHMSClient implements AutoCloseable {

  private static final Logger LOGGER = LoggerFactory.getLogger(SentryHMSClient.class);

  private final Configuration conf;
  private HiveMetaStoreClient client = null;
  private HiveConnectionFactory hiveConnectionFactory;

  private static final String SNAPSHOT = "snapshot";
  /** Measures time to get full snapshot. */
  private final Timer updateTimer = SentryMetrics.getInstance()
      .getTimer(name(FullUpdateInitializer.class, SNAPSHOT));
  /** Number of times update failed. */
  private final Counter failedSnapshotsCount = SentryMetrics.getInstance()
      .getCounter(name(FullUpdateInitializer.class, "failed"));

  public SentryHMSClient(Configuration conf, HiveConnectionFactory hiveConnectionFactory) {
    this.conf = conf;
    this.hiveConnectionFactory = hiveConnectionFactory;
  }

  /**
   * Used only for testing purposes.
   *x
   * @param client HiveMetaStoreClient to be initialized
   */
  @VisibleForTesting
  void setClient(HiveMetaStoreClient client) {
    this.client = client;
  }

  /**
   * Used to know if the client is connected to HMS
   *
   * @return true if the client is connected to HMS false, if client is not connected.
   */
  boolean isConnected() {
    return client != null;
  }

  /**
   * Connects to HMS by creating HiveMetaStoreClient.
   *
   * @throws IOException          if could not establish connection
   * @throws InterruptedException if connection was interrupted
   * @throws MetaException        if other errors happened
   */
  public void connect()
      throws IOException, InterruptedException, MetaException {
    if (client != null) {
      return;
    }
    client = hiveConnectionFactory.connect().getClient();
  }

  /**
   * Disconnects the HMS client.
   */
  public void disconnect() {
    try {
      if (client != null) {
        LOGGER.info("Closing the HMS client connection");
        client.close();
      }
    } catch (Exception e) {
      LOGGER.error("failed to close Hive Connection Factory", e);
    } finally {
      client = null;
    }
  }

  /**
   * Closes the HMS client.
   *
   * <p>This is similar to disconnect. As this class implements AutoClosable, close should be
   * implemented.
   */
  public void close() {
    disconnect();
  }

  /**
   * Creates HMS full snapshot.
   *
   * @return Full path snapshot and the last notification id on success
   */
  public PathsImage getFullSnapshot() throws Exception{
    if(!isConnected()) {
      try {
        connect();
      } catch (Exception e) {
        LOGGER.warn("Failed to connect to HMS Server. HMS may not be up. Will try again ", e);
        return new PathsImage(Collections.<String, Collection<String>>emptyMap(),
                SentryConstants.EMPTY_NOTIFICATION_ID, SentryConstants.EMPTY_PATHS_SNAPSHOT_ID);
      }
    }

    try {
      CurrentNotificationEventId eventIdBefore = client.getCurrentNotificationEventId();
      Map<String, Collection<String>> pathsFullSnapshot = fetchFullUpdate();
      if (pathsFullSnapshot.isEmpty()) {
        LOGGER.info("Received empty paths when getting full snapshot. NotificationID Before Snapshot: {}", eventIdBefore.getEventId());
        return new PathsImage(pathsFullSnapshot, SentryConstants.EMPTY_NOTIFICATION_ID,
            SentryConstants.EMPTY_PATHS_SNAPSHOT_ID);
      }

      CurrentNotificationEventId eventIdAfter = client.getCurrentNotificationEventId();
      LOGGER.info("NotificationID, Before Snapshot: {}, After Snapshot {}",
          eventIdBefore.getEventId(), eventIdAfter.getEventId());

      if (eventIdAfter.equals(eventIdBefore)) {
        LOGGER.info("Successfully fetched hive full snapshot, Current NotificationID: {}.",
                eventIdAfter);
        // As eventIDAfter is the last event that was processed, eventIDAfter is used to update
        // lastProcessedNotificationID instead of getting it from persistent store.
        return new PathsImage(pathsFullSnapshot, eventIdAfter.getEventId(),
                SentryConstants.EMPTY_PATHS_SNAPSHOT_ID);
      }

      LOGGER.info("Reconciling full snapshot - applying {} changes",
              eventIdAfter.getEventId() - eventIdBefore.getEventId());

      // While we were taking snapshot, HMS made some changes, so now we need to apply all
      // extra events to the snapshot
      long currentEventId = eventIdBefore.getEventId();
      MessageDeserializer deserializer = new SentryJSONMessageDeserializer();

      while (currentEventId < eventIdAfter.getEventId()) {
        NotificationEventResponse response =
                client.getNextNotification(currentEventId, Integer.MAX_VALUE, null);
        if (response == null || !response.isSetEvents() || response.getEvents().isEmpty()) {
          LOGGER.error("Snapshot discarded, updates to HMS data while shapshot is being taken."
                  + "ID Before: {}. ID After: {}", eventIdBefore.getEventId(), eventIdAfter.getEventId());
          return new PathsImage(Collections.<String, Collection<String>>emptyMap(),
                  SentryConstants.EMPTY_NOTIFICATION_ID, SentryConstants.EMPTY_PATHS_SNAPSHOT_ID);
        }

        for (NotificationEvent event : response.getEvents()) {
          LOGGER.info("Received event = {} currentEventId = {}, eventIdAfter = {}", event.getEventId(), currentEventId, eventIdAfter);
          if (event.getEventId() <= eventIdBefore.getEventId()) {
            LOGGER.error("Received stray event with eventId {} which is less then {}",
                    event.getEventId(), eventIdBefore);
            continue;
          }
          if (event.getEventId() > eventIdAfter.getEventId()) {
            // Enough events processed
            LOGGER.debug("Received eventId = {} is greater than eventIdAfter = {}", event.getEventId(), eventIdAfter);
            break;
          }
          try {
            FullUpdateModifier.applyEvent(pathsFullSnapshot, event, deserializer);
          } catch (Exception e) {
            LOGGER.warn("Failed to apply operation", e);
          }

          //Log warning message if event id increments are not sequential
          if( event.getEventId() != (currentEventId + 1) ) {
            LOGGER.warn("Received non-sequential event. currentEventId = {} received eventId = {} ", currentEventId, event.getEventId());
          }
          currentEventId = event.getEventId();
        }
      }

      LOGGER.info("Successfully fetched hive full snapshot, Current NotificationID: {}.",
          currentEventId);
      // As eventIDAfter is the last event that was processed, eventIDAfter is used to update
      // lastProcessedNotificationID instead of getting it from persistent store.
      return new PathsImage(pathsFullSnapshot, currentEventId,
        SentryConstants.EMPTY_PATHS_SNAPSHOT_ID);
    } catch (Exception exception) {
      LOGGER.error("Root Exception", ExceptionUtils.getRootCause(exception));
      if(exception instanceof TException) {
        LOGGER.error("Fetching new HMS snapshot failed because of HMS communication. HMS seems to be restarted. " +
                "Will try again.");
      } else {
        LOGGER.error("Fetching new HMS snapshot failed. Will try again.");
      }
      // Closing the connection towards HMS.
      close();
      return new PathsImage(Collections.<String, Collection<String>>emptyMap(),
              SentryConstants.EMPTY_NOTIFICATION_ID, SentryConstants.EMPTY_PATHS_SNAPSHOT_ID);
    }
  }

  /**
   * Retrieve a Hive full snapshot from HMS.
   *
   * @return HMS snapshot. Snapshot consists of a mapping from auth object name to the set of paths
   *     corresponding to that name.
   */
  private Map<String, Collection<String>> fetchFullUpdate() throws Exception{
    LOGGER.info("Request full HMS snapshot");
    try (FullUpdateInitializer updateInitializer =
             new FullUpdateInitializer(hiveConnectionFactory, conf);
         Context context = updateTimer.time()) {
      Map<String, Collection<String>> pathsUpdate = updateInitializer.getFullHMSSnapshot();
      LOGGER.info("Obtained full HMS snapshot");
      return pathsUpdate;
    } catch (Exception exception) {
      failedSnapshotsCount.inc();
      LOGGER.error("Snapshot created failed ", exception);
      throw exception;
    }
  }
}
