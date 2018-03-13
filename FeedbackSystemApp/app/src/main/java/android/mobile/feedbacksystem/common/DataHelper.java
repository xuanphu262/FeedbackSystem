package android.mobile.feedbacksystem.common;

import android.mobile.feedbacksystem.common.model.DeviceModel;
import android.mobile.feedbacksystem.common.model.LocationModel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hoainguyen on 3/13/18.
 */

public class DataHelper {
    public static List<LocationModel> locationList = new ArrayList<>();
    public static List<DeviceModel> deviceModelList = new ArrayList<>();

    public static void init() {

        // initial data for Location
        locationList.add(new LocationModel("1", "L1T1", "Toilet", new Date()));
        locationList.add(new LocationModel("2", "L1T2", "Toilet", new Date()));
        locationList.add(new LocationModel("3", "L1T3", "Toilet", new Date()));
        locationList.add(new LocationModel("4", "L1T4", "Toilet", new Date()));
        locationList.add(new LocationModel("5", "L2T1", "Toilet", new Date()));
        locationList.add(new LocationModel("6", "L2T2", "Toilet", new Date()));
        locationList.add(new LocationModel("7", "L1C1", "Counter", new Date()));
        locationList.add(new LocationModel("8", "L1C2", "Counter", new Date()));
        locationList.add(new LocationModel("9", "L2C1", "Counter", new Date()));
        locationList.add(new LocationModel("10", "L2C2", "Counter", new Date()));

        for(LocationModel locationModel: locationList) {
            deviceModelList.add(new DeviceModel("1", "Tablet " + locationModel.getId() , "9774d56d682e549c", new Date(), locationModel));
        }
    }
}
