package hoainguyen.lib.recyclerhelper.recycler.item.binding;

import hoainguyen.lib.recyclerhelper.recycler.item.RenderItem;

/**
 * Created by hoainguyen on 1/18/17.
 */

public abstract class BindingRecyclerViewRenderItem<VH extends BindingViewHolder> implements RenderItem<VH> {
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
