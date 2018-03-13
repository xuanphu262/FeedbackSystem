package android.mobile.feedbacksystem.common.model;

import java.util.Date;

/**
 * Created by hoainguyen on 3/13/18.
 */

public class DeviceModel {
    String id;
    String deviceName;
    String udid;
    Date createdDate;
    String locationId;
    DeviceStatus status = DeviceStatus.ONLINE;

    public DeviceModel(String id, String deviceName, String udid, Date createdDate, String locationId) {
        this.id = id;
        this.udid = udid;
        this.deviceName = deviceName;
        this.locationId = locationId;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public String getLocationId() {
        return locationId;
    };

    public String getDeviceName() { return deviceName; };

    public String getUdid() { return udid; };

    public DeviceStatus getDeviceStatus () { return status; };

}
