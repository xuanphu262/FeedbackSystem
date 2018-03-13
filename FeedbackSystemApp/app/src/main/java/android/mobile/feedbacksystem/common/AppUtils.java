package android.mobile.feedbacksystem.common;

import android.content.Context;
import android.mobile.feedbacksystem.common.model.DeviceModel;
import android.provider.Settings;
import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hoainguyen on 3/10/18.
 */

public class AppUtils {
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }


}
