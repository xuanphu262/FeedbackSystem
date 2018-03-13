package hoainguyen.lib.recyclerhelper.recycler.item.indicator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import hoainguyen.lib.recyclerhelper.R;
import hoainguyen.lib.recyclerhelper.recycler.item.RecyclerViewRenderItem;

/**
 * Created by hoainguyen on 1/19/17.
 */

public class LoadingIndicatorRenderItem extends RecyclerViewRenderItem<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder makeViewHolder(Context context) {
        return new RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.render_item_loading_indicator_layout, null)) {
        };
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder) {
        // Do nothing
    }

    @Override
    public boolean isFullSpan() {
        return true;
    }
}
