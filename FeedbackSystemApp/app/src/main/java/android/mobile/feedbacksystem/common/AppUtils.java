package android.mobile.feedbacksystem.common;

import android.content.Context;
import android.provider.Settings;

/**
 * Created by hoainguyen on 3/10/18.
 */

public class AppUtils {
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
