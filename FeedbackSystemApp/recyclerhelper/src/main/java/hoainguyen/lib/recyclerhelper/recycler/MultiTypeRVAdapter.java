package hoainguyen.lib.recyclerhelper.recycler;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hoainguyen.lib.recyclerhelper.recycler.item.RenderItem;

/**
 * Created by hoainguyen on 6/11/16.
 */
public class MultiTypeRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RenderItemRVAdapter, StackChildRVAdapter {
    protected Map<Integer, RenderItem> mTypeMap;
    List<RenderItem> mRenderItemList;


    public MultiTypeRVAdapter() {
        mTypeMap = new HashMap<>();
        mRenderItemList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mTypeMap.get(viewType).makeViewHolder(parent.getContext());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RenderItem renderItem = mRenderItemList.get(position);

        StaggeredGridLayoutManager.LayoutParams layoutParams;
        if (holder.itemView.getLayoutParams() == null) {
            layoutParams = new StaggeredGridLayoutManager.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        } else {
            layoutParams =
                    (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        }
        layoutParams.setFullSpan(renderItem.isFullSpan());
        holder.itemView.setLayoutParams(layoutParams);

        renderItem.bindViewHolder(holder);
    }

    @Override
    public int getItemCount() {
        return mRenderItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mRenderItemList.get(position).getType();
    }

    public void refresh(List<RenderItem> renderItems) {
        mRenderItemList.clear();
        mRenderItemList = renderItems;
        mTypeMap.clear();
        mapItemViewType(renderItems);
        notifyDataSetChanged();
    }

    @Override
    public void refresh(RenderItem renderItem) {
        mRenderItemList.clear();
        mRenderItemList.add(renderItem);
        mTypeMap.clear();
        mapItemViewType(renderItem);
        notifyDataSetChanged();
    }

    public void insert(int position, List<RenderItem> items) {
        mRenderItemList.addAll(position, items);
        mapItemViewType(items);
        notifyItemRangeInserted(position, items.size());
    }

    @Override
    public void insert(int position, RenderItem renderItem) {
        mRenderItemList.add(position, renderItem);
        mapItemViewType(renderItem);
        notifyItemInserted(position);
    }

    public void append(List<RenderItem> renderItems) {
        mRenderItemList.addAll(mRenderItemList.size(), renderItems);
        mapItemViewType(renderItems);
        notifyItemRangeInserted(mRenderItemList.size(), renderItems.size());
    }

    public void append(RenderItem renderItem) {
        mRenderItemList.add(renderItem);
        mapItemViewType(renderItem);
        notifyItemRangeInserted(mRenderItemList.size(), 1);
    }

    @Override
    public void remove(int position) {
        if (validatePosition(position)) {
            mRenderItemList.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public void clear() {
        mRenderItemList.clear();
        mTypeMap.clear();
        notifyDataSetChanged();
    }

    public void update(int position, RenderItem renderItem) {
        if (validatePosition(position)) {
            mRenderItemList.set(position, renderItem);
            notifyItemChanged(position);
        }
    }

    private boolean validatePosition(int position) {
        if (position < 0)
            return false;

        if (position > mRenderItemList.size())
            return false;

        return true;
    }

    public void move(int beforePosition, int afterPosition) {
        if (validatePosition(beforePosition) && validatePosition(afterPosition)) {
            RenderItem renderItem = mRenderItemList.get(beforePosition);
            mRenderItemList.remove(beforePosition);
            mRenderItemList.add(afterPosition, renderItem);
            notifyItemMoved(beforePosition, afterPosition);
        }
    }

    @Override
    public boolean containViewType(int viewType) {
        return (mTypeMap.containsKey(viewType));
    }

    @Override
    public void onPositionScrolled(int dy, int position) {
    }

    private void mapItemViewType(List<RenderItem> renderItems) {
        for (RenderItem renderItem : renderItems) {
            if (!mTypeMap.containsKey(renderItem.getType())) {
                mTypeMap.put(renderItem.getType(), renderItem);
            }
        }
    }

    private void mapItemViewType(RenderItem renderItem) {
        if (!mTypeMap.containsKey(renderItem.getType())) {
            mTypeMap.put(renderItem.getType(), renderItem);
        }
    }
}
