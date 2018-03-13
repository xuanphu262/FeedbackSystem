package android.mobile.feedbacksystem.common;

/**
 * Created by hoainguyen on 3/10/18.
 */

public enum Feedback {
    EXCELLENT(5), GOOD(4), AVERAGE(3), POOR(2), VERY_POOR(1);
    int value;

    Feedback(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
