package hoainguyen.lib.recyclerhelper.recycler;

/**
 * Created by hoainguyen on 3/28/17.
 */

public abstract class DataSectionRVAdapter extends MultiTypeRVAdapter {
    private boolean mLoading = false;

    public abstract void onStartLoadData();

    public boolean isLoading() {
        return mLoading;
    }

    public void setLoadingState(boolean isLoading) {
        this.mLoading = isLoading;
    }
}
