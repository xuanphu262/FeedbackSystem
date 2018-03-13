package hoainguyen.lib.recyclerhelper.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hoainguyen on 2/24/17.
 */

public class StackRecyclerAdapter extends RecyclerView.Adapter {
    private List<DataSectionRVAdapter> mAdapters = new ArrayList<>();
    private Map<DataSectionRVAdapter, StackRange> mStackRangeMap = new HashMap<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        for (DataSectionRVAdapter adapter : mAdapters) {
            if (adapter.containViewType(viewType)) {
                return adapter.onCreateViewHolder(parent, viewType);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DataSectionRVAdapter adapter = getChildAdapterForPosition(position);
        if (adapter != null) {
            adapter.onBindViewHolder(holder, computeChildPosition(adapter, position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        MultiTypeRVAdapter adapter = getChildAdapterForPosition(position);
        if (adapter != null)
            return adapter.getItemViewType(computeChildPosition(adapter, position));

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (MultiTypeRVAdapter adapter : mAdapters) {
            count += adapter.getItemCount();
        }
        return count;
    }

    public void appendAdapter(final DataSectionRVAdapter adapter) {
        mAdapters.add(adapter);
        reMappingStackRange();
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                reMappingStackRange();
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
                reMappingStackRange();
                notifyItemRangeChanged(mStackRangeMap.get(adapter).getPosition() + positionStart, itemCount);
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
                super.onItemRangeChanged(positionStart, itemCount, payload);
                reMappingStackRange();
                notifyItemRangeChanged(mStackRangeMap.get(adapter).getPosition() + positionStart, itemCount, payload);
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                reMappingStackRange();
                notifyItemRangeInserted(mStackRangeMap.get(adapter).getPosition() + positionStart, itemCount);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
                reMappingStackRange();
                notifyItemRangeRemoved(mStackRangeMap.get(adapter).getPosition() + positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                reMappingStackRange();
                notifyItemMoved(mStackRangeMap.get(adapter).getPosition() + fromPosition, mStackRangeMap.get(adapter).getPosition() + toPosition);
            }
        });
    }

    private void reMappingStackRange() {
        mStackRangeMap.clear();
        int mappedIndex = 0;
        for (int index = 0; index < mAdapters.size(); index++) {
            DataSectionRVAdapter childAdapter = mAdapters.get(index);
            mStackRangeMap.put(childAdapter, new StackRange(mappedIndex, childAdapter.getItemCount()));
            mappedIndex += childAdapter.getItemCount();
        }
    }

    private DataSectionRVAdapter getChildAdapterForPosition(int position) {
        for (DataSectionRVAdapter adapter : mAdapters) {
            if (mStackRangeMap.get(adapter).containPosition(position))
                return adapter;
        }
        return null;
    }

    private int computeChildPosition(StackChildRVAdapter adapter, int position) {
        StackRange range = mStackRangeMap.get(adapter);
        if (!range.containPosition(position))
            throw new IndexOutOfBoundsException("Adapter doesn't contain position");
        return position - range.getPosition();
    }

    void onPositionScrolled(int dy, int position) {
        DataSectionRVAdapter adapter = getChildAdapterForPosition(position);
        if (adapter != null) {
            StackRange stackRange = mStackRangeMap.get(adapter);
            adapter.onPositionScrolled(dy, position - stackRange.getPosition());
        }
    }

    public void start() {
        for (DataSectionRVAdapter adapter : mAdapters) {
            adapter.onStartLoadData();
        }
    }
}
