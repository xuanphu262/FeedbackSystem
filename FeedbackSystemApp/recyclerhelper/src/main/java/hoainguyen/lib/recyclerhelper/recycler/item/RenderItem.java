package hoainguyen.lib.recyclerhelper.recycler.item;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Created by hoainguyen on 1/18/17.
 */

public interface RenderItem<VH extends RecyclerView.ViewHolder> {
    boolean isFullSpan();

    VH makeViewHolder(Context context);

    void bindViewHolder(VH holder);

    int getType();

    String getItemId();
}
