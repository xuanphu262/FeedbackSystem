package hoainguyen.lib.recyclerhelper.recycler;

/**
 * Created by hoainguyen on 2/24/17.
 */

public interface StackChildRVAdapter {
    boolean containViewType(int viewType);

    void onPositionScrolled(int dy, int position);
}
