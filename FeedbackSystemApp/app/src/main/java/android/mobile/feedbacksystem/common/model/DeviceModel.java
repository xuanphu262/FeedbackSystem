package android.mobile.feedbacksystem.common.model;

import java.io.Serializable;
import java.util.Date;

public class DeviceModel implements Serializable {
    String id;
    String deviceName;
    String uuid;
    Date createdDate;
    String locationId;
    DeviceStatus status = DeviceStatus.ONLINE;

    public DeviceModel(String id, String deviceName, String uuid, Date createdDate, String locationId) {
        this.id = id;
        this.uuid = uuid;
        this.deviceName = deviceName;
        this.locationId = locationId;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getUuid() {
        return uuid;
    }

    public DeviceStatus getDeviceStatus() {
        return status;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
