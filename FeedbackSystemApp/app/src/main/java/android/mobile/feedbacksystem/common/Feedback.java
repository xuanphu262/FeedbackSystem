package android.mobile.feedbacksystem.common;

/**
 * Created by hoainguyen on 3/10/18.
 */

public enum Feedback {
    EXCELLENT("EXCELLENT"), GOOD("GOOD"), AVERAGE("AVERAGE"), POOR("POOR"), VERY_POOR("VERY POOR");
    String value;

    Feedback(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
