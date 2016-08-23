/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.sentry.tests.e2e.ha;

import org.apache.sentry.tests.e2e.hive.AbstractTestWithStaticConfiguration;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * End2End tests with Sentry service HA enabled.
 */
public class TestFailover extends AbstractTestWithStaticConfiguration {

  @BeforeClass
  public static void setupTestStaticConfiguration() throws Exception {
    useSentryService = true;
    enableSentryHA = true;
    clearDbPerTest = false;
    AbstractTestWithStaticConfiguration.setupTestStaticConfiguration();
  }


  /**
   * Test service failover. Run Sentry operations with shutting down one or more
   * of the services.
   * @throws Exception
   */
  @Test
  public void testFailover() throws Exception {
    int active = 0;
    int passive = 1;
    int maxTries = 10;
    //get the active service
    if (getSentrySrv().get(1).getActivator().isActive()) {
      active = 1;
      passive = 0;
    }

    Assert.assertEquals(Boolean.TRUE,getSentrySrv().get(active).getActivator().isActive());
    Assert.assertEquals(Boolean.FALSE,getSentrySrv().get(passive).getActivator().isActive());

    //stop the active service
    getSentrySrv().get(active).becomeStandby();

    for(int i = 0; i < maxTries ; i++) {
      if(!getSentrySrv().get(active).getActivator().isActive()) {
        break;
      }
      Thread.sleep(100);
    }
    //assert the active is now standby
    Assert.assertEquals(Boolean.FALSE,getSentrySrv().get(active).getActivator().isActive());
    //wait for the passive to become active
    for(int i = 0; i < maxTries ; i++) {
      if(getSentrySrv().get(passive).getActivator().isActive()) {
        break;
      }
      Thread.sleep(100);
    }
    //assert that the previously passive service is now  active
    Assert.assertEquals(Boolean.TRUE,getSentrySrv().get(passive).getActivator().isActive());
  }

}