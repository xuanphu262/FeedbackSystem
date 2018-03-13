package android.mobile.feedbacksystem.common.model;

import java.util.Date;

/**
 * Created by hoainguyen on 3/13/18.
 */

public class FeedbackModel {
    String id;
    String rating;
    String deviceId;
    String locationId;
    Date createdDate;

    public FeedbackModel(String id, String rating, String deviceId, String locationId, Date createdDate) {
        this.id = id;
        this.rating = rating;
        this.deviceId = deviceId;
        this.locationId = locationId;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public String getRating() {
        return rating;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getLocationId() {
        return locationId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

}
