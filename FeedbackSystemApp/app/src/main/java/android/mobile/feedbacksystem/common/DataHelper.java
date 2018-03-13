package android.mobile.feedbacksystem.common;

import android.content.Context;
import android.mobile.feedbacksystem.common.model.DeviceModel;
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
    public static List<DeviceModel> deviceModelList = new ArrayList<>();

    public static void initialData(Context context) throws IOException {
        InputStream locationStream = context.getAssets().open("locations.json");

        locationList = readLocationsStream(locationStream);
        for (LocationModel locationModel : locationList) {
            deviceModelList.add(new DeviceModel("1", "Tablet " + locationModel.getId(), "9774d56d682e549c", new Date(), locationModel.getId()));
        }
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
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
        DeviceModel device = null;

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
}
