package android.mobile.feedbacksystem.common.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by hoainguyen on 3/11/18.
 */

public class LocationModel {
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
}
