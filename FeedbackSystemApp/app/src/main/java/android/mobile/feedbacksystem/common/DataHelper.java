package android.mobile.feedbacksystem.common;

import android.content.Context;
import android.mobile.feedbacksystem.common.model.DeviceModel;
import android.mobile.feedbacksystem.common.model.FeedbackModel;
import android.mobile.feedbacksystem.common.model.LocationModel;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hoainguyen on 3/13/18.
 */

public class DataHelper {
    public static List<LocationModel> locationList = new ArrayList<>();
    public static List<DeviceModel> deviceList = new ArrayList<>();
    public static List<FeedbackModel> feedbackList = new ArrayList<>();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public static void initialData(Context context) throws IOException {

        locationList = loadLocation(context);
        for (LocationModel locationModel : locationList) {
            deviceList.add(new DeviceModel("1", "Tablet " + locationModel.getId(), "9774d56d682e549c", new Date(), locationModel.getId()));
        }
        feedbackList = loadFeedback(context);

    }

    private static List<FeedbackModel> loadFeedback (Context context) throws IOException {
        InputStream feedbackStream = context.getAssets().open("feedbacks.json");
        return readFeedbackStream(feedbackStream);
    }


    private static List<FeedbackModel> readFeedbackStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readeFeedbacks(reader);
        } finally {
            reader.close();
        }
    }

    private static List<FeedbackModel> readeFeedbacks(JsonReader reader) throws IOException {
        List<FeedbackModel> feedbacks = new ArrayList<FeedbackModel>();

        reader.beginArray();
        while (reader.hasNext()) {
            feedbacks.add(readFeedback(reader));
        }
        reader.endArray();
        return feedbacks;
    }

    private static FeedbackModel readFeedback(JsonReader reader) throws IOException {
        String id = null;
        String rating = null;
        String deviceId = null;
        String locationId = null;
        Date createDated = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                id = reader.nextString();
            } else if (name.equals("rating")) {
                rating = reader.nextString();
            } else if (name.equals("deviceId")) {
                deviceId = reader.nextString();
            } else if (name.equals("locationId")) {
                locationId = reader.nextString();
            } else if (name.equals("createdDate")) {
                try {
                    createDated = dateFormat.parse(reader.nextString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ;
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new FeedbackModel(id, rating, deviceId, locationId, createDated);
    }

    public static List<LocationModel> loadLocation(Context context) throws IOException {
        InputStream locationStream = context.getAssets().open("locations.json");
        return readLocationsStream(locationStream);
    }


    private static List<LocationModel> readLocationsStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readeLocations(reader);
        } finally {
            reader.close();
        }
    }

    private static List<LocationModel> readeLocations(JsonReader reader) throws IOException {
        List<LocationModel> locations = new ArrayList<LocationModel>();

        reader.beginArray();
        while (reader.hasNext()) {
            locations.add(readeLocation(reader));
        }
        reader.endArray();
        return locations;
    }

    private static LocationModel readeLocation(JsonReader reader) throws IOException {
        String id = null;
        String shortDescription = null;
        String longDescription = null;
        Date createDated = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                id = reader.nextString();
            } else if (name.equals("shortDescription")) {
                shortDescription = reader.nextString();
            } else if (name.equals("longDescription")) {
                longDescription = reader.nextString();
            } else if (name.equals("createdDate")) {
                try {
                    createDated = dateFormat.parse(reader.nextString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ;
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new LocationModel(id, shortDescription, longDescription, createDated);
    }

    private static List<DeviceModel> readDevicesStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readeDevices(reader);
        } finally {
            reader.close();
        }
    }

    private static List<DeviceModel> readeDevices(JsonReader reader) throws IOException {
        List<DeviceModel> devices = new ArrayList<DeviceModel>();

        reader.beginArray();
        while (reader.hasNext()) {
            devices.add(readeDevice(reader));
        }
        reader.endArray();
        return devices;
    }

    private static DeviceModel readeDevice(JsonReader reader) throws IOException {
        String id = null;
        String deviceName = null;
        String udid = null;
        String locationId = null;
        Date createDated = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                id = reader.nextString();
            } else if (name.equals("deviceName")) {
                deviceName = reader.nextString();
            } else if (name.equals("udid")) {
                udid = reader.nextString();
            } else if (name.equals("createdDate")) {
                createDated = new Date(reader.nextString());
            } else if (name.equals("locationId")) {
                locationId = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new DeviceModel(id, deviceName, udid, createDated, locationId);
    }

    public static void addLocation(LocationModel locationModel) {
        locationList.add(locationModel);
    }

    public static void addDevice(DeviceModel device) {
        deviceList.add(device);
    }

    public static String getNewLocationId() {
        return (DataHelper.locationList.size() + 1) + "";
    }

    public static String getNewDeviceID() {
        return (DataHelper.deviceList.size() + 1) + "";
    }

    public static boolean updateLocation(LocationModel location) {
        for (int i = 0; i < locationList.size(); i ++) {
            if (locationList.get(i).getId().equals(location.getId())){
                locationList.remove(i);
                locationList.add(i, location);
                return true;
            }
        }
        return false;
    }

    public static boolean updateDevice(DeviceModel device) {
        for (int i = 0; i < locationList.size(); i ++) {
            if (deviceList.get(i).getId().equals(device.getId())){
                deviceList.remove(i);
                deviceList.add(i, device);
                return true;
            }
        }
        return false;
    }

    public static List<FeedbackModel> getFeedbackList(Date startDate, Date endDate, String locationId) {

        List<FeedbackModel> feedbacks = null;
        long time = 0;
        for (int i = 0; i < feedbackList.size(); i ++) {
            time = feedbackList.get(i).getCreatedDate().getTime();
            if( time >= startDate.getTime() && time <= endDate.getTime() &&  feedbackList.get(i).getLocationId().equals(locationId) ){
                feedbacks.add(feedbackList.get(i));
            }
        }
        return feedbacks;
    }


}
