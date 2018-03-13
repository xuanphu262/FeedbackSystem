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
    LocationModel location;
    DeviceStatus status = DeviceStatus.ONLINE;

    public DeviceModel(String id, String deviceName, String udid, Date createdDate, LocationModel location) {
        this.id = id;
        this.udid = udid;
        this.deviceName = deviceName;
        this.location = location;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public LocationModel getLocation() {
        return location;
    };

    public String getDeviceName() { return deviceName; };

    public String getUdid() { return udid; };

    public DeviceStatus getDeviceStatus () { return status; };

}
