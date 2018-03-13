package android.mobile.feedbacksystem.common.model;

/**
 * Created by hoainguyen on 3/11/18.
 */

public class LocationModel {
    String id;
    String shortDescription;
    String longDescription;

    public LocationModel(String id, String shortDescription, String longDescription) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
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
}
