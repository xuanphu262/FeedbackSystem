package hoainguyen.lib.recyclerhelper.recycler.item;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

/**
 * Created by hoainguyen on 3/7/17.
 */

public class StaticRenderItem extends RecyclerViewRenderItem<RecyclerView.ViewHolder> {
    private int mLayoutId;
    private boolean mIsFullSpan = false;

    public StaticRenderItem(int layoutId, boolean isFullSpan) {
        mLayoutId = layoutId;
        mIsFullSpan = isFullSpan;
    }

    @Override
    public boolean isFullSpan() {
        return mIsFullSpan;
    }

    @Override
    public RecyclerView.ViewHolder makeViewHolder(Context context) {
        return new RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(mLayoutId, null)) {
        };
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder) {
        // So we do nothing here
    }

    @Override
    public int getType() {
        // make different type between layouts
        return super.getType() + mLayoutId;
    }
}
