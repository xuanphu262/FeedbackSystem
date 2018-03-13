package hoainguyen.lib.recyclerhelper;

/**
 * Created by hoainguyen on 3/28/17.
 */

public class Utils {
    public static int findMaxValue(int[] array) {
        int maxValue = Integer.MIN_VALUE;
        for (int position : array) {
            maxValue = position > maxValue ? position : maxValue;
        }
        return maxValue;
    }

    public static int findMinValue(int[] array) {
        int minValue = Integer.MAX_VALUE;
        for (int position : array) {
            minValue = position < minValue ? position : minValue;
        }
        return minValue;
    }
}
