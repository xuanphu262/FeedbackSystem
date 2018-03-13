package hoainguyen.lib.recyclerhelper.recycler;

/**
 * Created by hoainguyen on 2/24/17.
 */

public class StackRange {
    private int mPosition;
    private int mLength;

    public StackRange(int position, int length) {
        this.mPosition = position;
        this.mLength = length;
    }

    public int getLength() {
        return mLength;
    }

    public int getPosition() {
        return mPosition;
    }

    public boolean containPosition(int position) {
        if (mLength <= 0)
            return false;

        if (position < mPosition)
            return false;

        if (position > mPosition + mLength - 1)
            return false;
        return true;
    }
}
