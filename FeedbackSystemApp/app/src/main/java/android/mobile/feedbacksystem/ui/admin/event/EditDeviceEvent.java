package android.mobile.feedbacksystem.ui.admin.event;

import android.mobile.feedbacksystem.common.model.DeviceModel;

/**
 * Created by hoainguyen on 3/14/18.
 */

public class EditDeviceEvent {
    private DeviceModel device;

    public EditDeviceEvent(DeviceModel deviceModel) {
        this.device = deviceModel;
    }

    public DeviceModel getDevice() {
        return device;
    }
}
