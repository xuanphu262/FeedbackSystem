package android.mobile.feedbacksystem.ui.admin.event;

import android.mobile.feedbacksystem.common.model.LocationModel;

/**
 * Created by hoainguyen on 3/14/18.
 */

public class EditLocationEvent {
    private LocationModel location;

    public EditLocationEvent(LocationModel locationModel) {
        this.location = locationModel;
    }

    public LocationModel getLocation() {
        return location;
    }
}
