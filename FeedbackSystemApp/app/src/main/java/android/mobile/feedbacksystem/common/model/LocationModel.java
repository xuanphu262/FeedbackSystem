package android.mobile.feedbacksystem.common.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class LocationModel implements Serializable {
    String id;
    String shortDescription;
    String longDescription;
    Date createdDate;

    public LocationModel(String id, String shortDescription, String longDescription, Date createdDate) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
