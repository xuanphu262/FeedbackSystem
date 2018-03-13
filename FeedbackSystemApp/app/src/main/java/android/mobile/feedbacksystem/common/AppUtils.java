package android.mobile.feedbacksystem.common;

import android.content.Context;
import android.mobile.feedbacksystem.R;
import android.provider.Settings;

/**
 * Created by hoainguyen on 3/10/18.
 */

public class AppUtils {
    public static int[] FeedbackResource = {
            R.drawable.ic_feedback_1,
            R.drawable.ic_feedback_2,
            R.drawable.ic_feedback_3,
            R.drawable.ic_feedback_4,
            R.drawable.ic_feedback_5
    };

    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
