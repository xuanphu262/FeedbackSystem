package android.mobile.feedbacksystem.common.model;

/**
 * Created by hoainguyen on 3/13/18.
 */

public class DeviceModel {
    String id;
    LocationModel location;
    DeviceStatus status = DeviceStatus.ONLINE;

    public DeviceModel(String id, LocationModel location) {
        this.id = id;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public LocationModel getLocation() {
        return location;
    }
}
