/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.sentry.api.service.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)")
public class TSentryHmsEventNotification implements org.apache.thrift.TBase<TSentryHmsEventNotification, TSentryHmsEventNotification._Fields>, java.io.Serializable, Cloneable, Comparable<TSentryHmsEventNotification> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TSentryHmsEventNotification");

  private static final org.apache.thrift.protocol.TField PROTOCOL_VERSION_FIELD_DESC = new org.apache.thrift.protocol.TField("protocol_version", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField REQUESTOR_USER_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("requestorUserName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField EVENT_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("eventType", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField AUTHORIZABLE_FIELD_DESC = new org.apache.thrift.protocol.TField("authorizable", org.apache.thrift.protocol.TType.STRUCT, (short)5);
  private static final org.apache.thrift.protocol.TField OWNER_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("ownerType", org.apache.thrift.protocol.TType.I32, (short)6);
  private static final org.apache.thrift.protocol.TField OWNER_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("ownerName", org.apache.thrift.protocol.TType.STRING, (short)7);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TSentryHmsEventNotificationStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TSentryHmsEventNotificationTupleSchemeFactory());
  }

  private int protocol_version; // required
  private String requestorUserName; // required
  private long id; // required
  private String eventType; // required
  private TSentryAuthorizable authorizable; // required
  private TSentryObjectOwnerType ownerType; // optional
  private String ownerName; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PROTOCOL_VERSION((short)1, "protocol_version"),
    REQUESTOR_USER_NAME((short)2, "requestorUserName"),
    ID((short)3, "id"),
    EVENT_TYPE((short)4, "eventType"),
    AUTHORIZABLE((short)5, "authorizable"),
    /**
     * 
     * @see TSentryObjectOwnerType
     */
    OWNER_TYPE((short)6, "ownerType"),
    OWNER_NAME((short)7, "ownerName");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // PROTOCOL_VERSION
          return PROTOCOL_VERSION;
        case 2: // REQUESTOR_USER_NAME
          return REQUESTOR_USER_NAME;
        case 3: // ID
          return ID;
        case 4: // EVENT_TYPE
          return EVENT_TYPE;
        case 5: // AUTHORIZABLE
          return AUTHORIZABLE;
        case 6: // OWNER_TYPE
          return OWNER_TYPE;
        case 7: // OWNER_NAME
          return OWNER_NAME;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __PROTOCOL_VERSION_ISSET_ID = 0;
  private static final int __ID_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.OWNER_TYPE,_Fields.OWNER_NAME};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PROTOCOL_VERSION, new org.apache.thrift.meta_data.FieldMetaData("protocol_version", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.REQUESTOR_USER_NAME, new org.apache.thrift.meta_data.FieldMetaData("requestorUserName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.EVENT_TYPE, new org.apache.thrift.meta_data.FieldMetaData("eventType", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.AUTHORIZABLE, new org.apache.thrift.meta_data.FieldMetaData("authorizable", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TSentryAuthorizable.class)));
    tmpMap.put(_Fields.OWNER_TYPE, new org.apache.thrift.meta_data.FieldMetaData("ownerType", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, TSentryObjectOwnerType.class)));
    tmpMap.put(_Fields.OWNER_NAME, new org.apache.thrift.meta_data.FieldMetaData("ownerName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TSentryHmsEventNotification.class, metaDataMap);
  }

  public TSentryHmsEventNotification() {
    this.protocol_version = 2;

  }

  public TSentryHmsEventNotification(
    int protocol_version,
    String requestorUserName,
    long id,
    String eventType,
    TSentryAuthorizable authorizable)
  {
    this();
    this.protocol_version = protocol_version;
    setProtocol_versionIsSet(true);
    this.requestorUserName = requestorUserName;
    this.id = id;
    setIdIsSet(true);
    this.eventType = eventType;
    this.authorizable = authorizable;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TSentryHmsEventNotification(TSentryHmsEventNotification other) {
    __isset_bitfield = other.__isset_bitfield;
    this.protocol_version = other.protocol_version;
    if (other.isSetRequestorUserName()) {
      this.requestorUserName = other.requestorUserName;
    }
    this.id = other.id;
    if (other.isSetEventType()) {
      this.eventType = other.eventType;
    }
    if (other.isSetAuthorizable()) {
      this.authorizable = new TSentryAuthorizable(other.authorizable);
    }
    if (other.isSetOwnerType()) {
      this.ownerType = other.ownerType;
    }
    if (other.isSetOwnerName()) {
      this.ownerName = other.ownerName;
    }
  }

  public TSentryHmsEventNotification deepCopy() {
    return new TSentryHmsEventNotification(this);
  }

  @Override
  public void clear() {
    this.protocol_version = 2;

    this.requestorUserName = null;
    setIdIsSet(false);
    this.id = 0;
    this.eventType = null;
    this.authorizable = null;
    this.ownerType = null;
    this.ownerName = null;
  }

  public int getProtocol_version() {
    return this.protocol_version;
  }

  public void setProtocol_version(int protocol_version) {
    this.protocol_version = protocol_version;
    setProtocol_versionIsSet(true);
  }

  public void unsetProtocol_version() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PROTOCOL_VERSION_ISSET_ID);
  }

  /** Returns true if field protocol_version is set (has been assigned a value) and false otherwise */
  public boolean isSetProtocol_version() {
    return EncodingUtils.testBit(__isset_bitfield, __PROTOCOL_VERSION_ISSET_ID);
  }

  public void setProtocol_versionIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PROTOCOL_VERSION_ISSET_ID, value);
  }

  public String getRequestorUserName() {
    return this.requestorUserName;
  }

  public void setRequestorUserName(String requestorUserName) {
    this.requestorUserName = requestorUserName;
  }

  public void unsetRequestorUserName() {
    this.requestorUserName = null;
  }

  /** Returns true if field requestorUserName is set (has been assigned a value) and false otherwise */
  public boolean isSetRequestorUserName() {
    return this.requestorUserName != null;
  }

  public void setRequestorUserNameIsSet(boolean value) {
    if (!value) {
      this.requestorUserName = null;
    }
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
    setIdIsSet(true);
  }

  public void unsetId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public String getEventType() {
    return this.eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  public void unsetEventType() {
    this.eventType = null;
  }

  /** Returns true if field eventType is set (has been assigned a value) and false otherwise */
  public boolean isSetEventType() {
    return this.eventType != null;
  }

  public void setEventTypeIsSet(boolean value) {
    if (!value) {
      this.eventType = null;
    }
  }

  public TSentryAuthorizable getAuthorizable() {
    return this.authorizable;
  }

  public void setAuthorizable(TSentryAuthorizable authorizable) {
    this.authorizable = authorizable;
  }

  public void unsetAuthorizable() {
    this.authorizable = null;
  }

  /** Returns true if field authorizable is set (has been assigned a value) and false otherwise */
  public boolean isSetAuthorizable() {
    return this.authorizable != null;
  }

  public void setAuthorizableIsSet(boolean value) {
    if (!value) {
      this.authorizable = null;
    }
  }

  /**
   * 
   * @see TSentryObjectOwnerType
   */
  public TSentryObjectOwnerType getOwnerType() {
    return this.ownerType;
  }

  /**
   * 
   * @see TSentryObjectOwnerType
   */
  public void setOwnerType(TSentryObjectOwnerType ownerType) {
    this.ownerType = ownerType;
  }

  public void unsetOwnerType() {
    this.ownerType = null;
  }

  /** Returns true if field ownerType is set (has been assigned a value) and false otherwise */
  public boolean isSetOwnerType() {
    return this.ownerType != null;
  }

  public void setOwnerTypeIsSet(boolean value) {
    if (!value) {
      this.ownerType = null;
    }
  }

  public String getOwnerName() {
    return this.ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  public void unsetOwnerName() {
    this.ownerName = null;
  }

  /** Returns true if field ownerName is set (has been assigned a value) and false otherwise */
  public boolean isSetOwnerName() {
    return this.ownerName != null;
  }

  public void setOwnerNameIsSet(boolean value) {
    if (!value) {
      this.ownerName = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case PROTOCOL_VERSION:
      if (value == null) {
        unsetProtocol_version();
      } else {
        setProtocol_version((Integer)value);
      }
      break;

    case REQUESTOR_USER_NAME:
      if (value == null) {
        unsetRequestorUserName();
      } else {
        setRequestorUserName((String)value);
      }
      break;

    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Long)value);
      }
      break;

    case EVENT_TYPE:
      if (value == null) {
        unsetEventType();
      } else {
        setEventType((String)value);
      }
      break;

    case AUTHORIZABLE:
      if (value == null) {
        unsetAuthorizable();
      } else {
        setAuthorizable((TSentryAuthorizable)value);
      }
      break;

    case OWNER_TYPE:
      if (value == null) {
        unsetOwnerType();
      } else {
        setOwnerType((TSentryObjectOwnerType)value);
      }
      break;

    case OWNER_NAME:
      if (value == null) {
        unsetOwnerName();
      } else {
        setOwnerName((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case PROTOCOL_VERSION:
      return getProtocol_version();

    case REQUESTOR_USER_NAME:
      return getRequestorUserName();

    case ID:
      return getId();

    case EVENT_TYPE:
      return getEventType();

    case AUTHORIZABLE:
      return getAuthorizable();

    case OWNER_TYPE:
      return getOwnerType();

    case OWNER_NAME:
      return getOwnerName();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case PROTOCOL_VERSION:
      return isSetProtocol_version();
    case REQUESTOR_USER_NAME:
      return isSetRequestorUserName();
    case ID:
      return isSetId();
    case EVENT_TYPE:
      return isSetEventType();
    case AUTHORIZABLE:
      return isSetAuthorizable();
    case OWNER_TYPE:
      return isSetOwnerType();
    case OWNER_NAME:
      return isSetOwnerName();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TSentryHmsEventNotification)
      return this.equals((TSentryHmsEventNotification)that);
    return false;
  }

  public boolean equals(TSentryHmsEventNotification that) {
    if (that == null)
      return false;

    boolean this_present_protocol_version = true;
    boolean that_present_protocol_version = true;
    if (this_present_protocol_version || that_present_protocol_version) {
      if (!(this_present_protocol_version && that_present_protocol_version))
        return false;
      if (this.protocol_version != that.protocol_version)
        return false;
    }

    boolean this_present_requestorUserName = true && this.isSetRequestorUserName();
    boolean that_present_requestorUserName = true && that.isSetRequestorUserName();
    if (this_present_requestorUserName || that_present_requestorUserName) {
      if (!(this_present_requestorUserName && that_present_requestorUserName))
        return false;
      if (!this.requestorUserName.equals(that.requestorUserName))
        return false;
    }

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_eventType = true && this.isSetEventType();
    boolean that_present_eventType = true && that.isSetEventType();
    if (this_present_eventType || that_present_eventType) {
      if (!(this_present_eventType && that_present_eventType))
        return false;
      if (!this.eventType.equals(that.eventType))
        return false;
    }

    boolean this_present_authorizable = true && this.isSetAuthorizable();
    boolean that_present_authorizable = true && that.isSetAuthorizable();
    if (this_present_authorizable || that_present_authorizable) {
      if (!(this_present_authorizable && that_present_authorizable))
        return false;
      if (!this.authorizable.equals(that.authorizable))
        return false;
    }

    boolean this_present_ownerType = true && this.isSetOwnerType();
    boolean that_present_ownerType = true && that.isSetOwnerType();
    if (this_present_ownerType || that_present_ownerType) {
      if (!(this_present_ownerType && that_present_ownerType))
        return false;
      if (!this.ownerType.equals(that.ownerType))
        return false;
    }

    boolean this_present_ownerName = true && this.isSetOwnerName();
    boolean that_present_ownerName = true && that.isSetOwnerName();
    if (this_present_ownerName || that_present_ownerName) {
      if (!(this_present_ownerName && that_present_ownerName))
        return false;
      if (!this.ownerName.equals(that.ownerName))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_protocol_version = true;
    list.add(present_protocol_version);
    if (present_protocol_version)
      list.add(protocol_version);

    boolean present_requestorUserName = true && (isSetRequestorUserName());
    list.add(present_requestorUserName);
    if (present_requestorUserName)
      list.add(requestorUserName);

    boolean present_id = true;
    list.add(present_id);
    if (present_id)
      list.add(id);

    boolean present_eventType = true && (isSetEventType());
    list.add(present_eventType);
    if (present_eventType)
      list.add(eventType);

    boolean present_authorizable = true && (isSetAuthorizable());
    list.add(present_authorizable);
    if (present_authorizable)
      list.add(authorizable);

    boolean present_ownerType = true && (isSetOwnerType());
    list.add(present_ownerType);
    if (present_ownerType)
      list.add(ownerType.getValue());

    boolean present_ownerName = true && (isSetOwnerName());
    list.add(present_ownerName);
    if (present_ownerName)
      list.add(ownerName);

    return list.hashCode();
  }

  @Override
  public int compareTo(TSentryHmsEventNotification other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetProtocol_version()).compareTo(other.isSetProtocol_version());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetProtocol_version()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.protocol_version, other.protocol_version);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRequestorUserName()).compareTo(other.isSetRequestorUserName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRequestorUserName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.requestorUserName, other.requestorUserName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEventType()).compareTo(other.isSetEventType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEventType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.eventType, other.eventType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAuthorizable()).compareTo(other.isSetAuthorizable());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAuthorizable()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.authorizable, other.authorizable);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetOwnerType()).compareTo(other.isSetOwnerType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOwnerType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ownerType, other.ownerType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetOwnerName()).compareTo(other.isSetOwnerName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOwnerName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ownerName, other.ownerName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TSentryHmsEventNotification(");
    boolean first = true;

    sb.append("protocol_version:");
    sb.append(this.protocol_version);
    first = false;
    if (!first) sb.append(", ");
    sb.append("requestorUserName:");
    if (this.requestorUserName == null) {
      sb.append("null");
    } else {
      sb.append(this.requestorUserName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("eventType:");
    if (this.eventType == null) {
      sb.append("null");
    } else {
      sb.append(this.eventType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("authorizable:");
    if (this.authorizable == null) {
      sb.append("null");
    } else {
      sb.append(this.authorizable);
    }
    first = false;
    if (isSetOwnerType()) {
      if (!first) sb.append(", ");
      sb.append("ownerType:");
      if (this.ownerType == null) {
        sb.append("null");
      } else {
        sb.append(this.ownerType);
      }
      first = false;
    }
    if (isSetOwnerName()) {
      if (!first) sb.append(", ");
      sb.append("ownerName:");
      if (this.ownerName == null) {
        sb.append("null");
      } else {
        sb.append(this.ownerName);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetProtocol_version()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'protocol_version' is unset! Struct:" + toString());
    }

    if (!isSetRequestorUserName()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'requestorUserName' is unset! Struct:" + toString());
    }

    if (!isSetId()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'id' is unset! Struct:" + toString());
    }

    if (!isSetEventType()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'eventType' is unset! Struct:" + toString());
    }

    if (!isSetAuthorizable()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'authorizable' is unset! Struct:" + toString());
    }

    // check for sub-struct validity
    if (authorizable != null) {
      authorizable.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TSentryHmsEventNotificationStandardSchemeFactory implements SchemeFactory {
    public TSentryHmsEventNotificationStandardScheme getScheme() {
      return new TSentryHmsEventNotificationStandardScheme();
    }
  }

  private static class TSentryHmsEventNotificationStandardScheme extends StandardScheme<TSentryHmsEventNotification> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TSentryHmsEventNotification struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PROTOCOL_VERSION
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.protocol_version = iprot.readI32();
              struct.setProtocol_versionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // REQUESTOR_USER_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.requestorUserName = iprot.readString();
              struct.setRequestorUserNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.id = iprot.readI64();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // EVENT_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.eventType = iprot.readString();
              struct.setEventTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // AUTHORIZABLE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.authorizable = new TSentryAuthorizable();
              struct.authorizable.read(iprot);
              struct.setAuthorizableIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // OWNER_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.ownerType = org.apache.sentry.api.service.thrift.TSentryObjectOwnerType.findByValue(iprot.readI32());
              struct.setOwnerTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // OWNER_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.ownerName = iprot.readString();
              struct.setOwnerNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TSentryHmsEventNotification struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(PROTOCOL_VERSION_FIELD_DESC);
      oprot.writeI32(struct.protocol_version);
      oprot.writeFieldEnd();
      if (struct.requestorUserName != null) {
        oprot.writeFieldBegin(REQUESTOR_USER_NAME_FIELD_DESC);
        oprot.writeString(struct.requestorUserName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI64(struct.id);
      oprot.writeFieldEnd();
      if (struct.eventType != null) {
        oprot.writeFieldBegin(EVENT_TYPE_FIELD_DESC);
        oprot.writeString(struct.eventType);
        oprot.writeFieldEnd();
      }
      if (struct.authorizable != null) {
        oprot.writeFieldBegin(AUTHORIZABLE_FIELD_DESC);
        struct.authorizable.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.ownerType != null) {
        if (struct.isSetOwnerType()) {
          oprot.writeFieldBegin(OWNER_TYPE_FIELD_DESC);
          oprot.writeI32(struct.ownerType.getValue());
          oprot.writeFieldEnd();
        }
      }
      if (struct.ownerName != null) {
        if (struct.isSetOwnerName()) {
          oprot.writeFieldBegin(OWNER_NAME_FIELD_DESC);
          oprot.writeString(struct.ownerName);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TSentryHmsEventNotificationTupleSchemeFactory implements SchemeFactory {
    public TSentryHmsEventNotificationTupleScheme getScheme() {
      return new TSentryHmsEventNotificationTupleScheme();
    }
  }

  private static class TSentryHmsEventNotificationTupleScheme extends TupleScheme<TSentryHmsEventNotification> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TSentryHmsEventNotification struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.protocol_version);
      oprot.writeString(struct.requestorUserName);
      oprot.writeI64(struct.id);
      oprot.writeString(struct.eventType);
      struct.authorizable.write(oprot);
      BitSet optionals = new BitSet();
      if (struct.isSetOwnerType()) {
        optionals.set(0);
      }
      if (struct.isSetOwnerName()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetOwnerType()) {
        oprot.writeI32(struct.ownerType.getValue());
      }
      if (struct.isSetOwnerName()) {
        oprot.writeString(struct.ownerName);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TSentryHmsEventNotification struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.protocol_version = iprot.readI32();
      struct.setProtocol_versionIsSet(true);
      struct.requestorUserName = iprot.readString();
      struct.setRequestorUserNameIsSet(true);
      struct.id = iprot.readI64();
      struct.setIdIsSet(true);
      struct.eventType = iprot.readString();
      struct.setEventTypeIsSet(true);
      struct.authorizable = new TSentryAuthorizable();
      struct.authorizable.read(iprot);
      struct.setAuthorizableIsSet(true);
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.ownerType = org.apache.sentry.api.service.thrift.TSentryObjectOwnerType.findByValue(iprot.readI32());
        struct.setOwnerTypeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.ownerName = iprot.readString();
        struct.setOwnerNameIsSet(true);
      }
    }
  }

}

