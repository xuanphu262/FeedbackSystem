package android.mobile.feedbacksystem.common;

import android.mobile.feedbacksystem.common.model.DeviceModel;
import android.mobile.feedbacksystem.common.model.LocationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoainguyen on 3/13/18.
 */

public class DataHelper {
    public static List<LocationModel> locationList = new ArrayList<>();
    public static List<DeviceModel> deviceModelList = new ArrayList<>();

    public static void init() {
        locationList.add(new LocationModel("1", "toilet1", "in H1"));
        locationList.add(new LocationModel("2", "toilet2", "in H1"));
        locationList.add(new LocationModel("3", "toilet3", "in H1"));
        locationList.add(new LocationModel("4", "toilet4", "in H1"));

        for(LocationModel locationModel: locationList) {
            deviceModelList.add(new DeviceModel(locationModel.getId(), locationModel));
        }
    }
}
