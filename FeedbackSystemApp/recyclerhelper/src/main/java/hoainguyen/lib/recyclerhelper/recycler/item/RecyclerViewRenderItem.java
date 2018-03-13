package hoainguyen.lib.recyclerhelper.recycler.item;

import android.support.v7.widget.RecyclerView;

/**
 * Created by hoainguyen on 6/11/16.
 */
public abstract class RecyclerViewRenderItem<VH extends RecyclerView.ViewHolder> implements RenderItem<VH> {
    @Override
    public boolean isFullSpan() {
        return false;
    }

    @Override
    public int getType() {
        return this.getClass().hashCode();
    }

    @Override
    public String getItemId() {
        return "";
    }
}
