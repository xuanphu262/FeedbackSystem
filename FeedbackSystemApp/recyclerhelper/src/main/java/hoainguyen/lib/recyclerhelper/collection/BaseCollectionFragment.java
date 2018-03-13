package hoainguyen.lib.recyclerhelper.collection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hoainguyen.lib.recyclerhelper.R;
import hoainguyen.lib.recyclerhelper.recycler.MultiTypeRVAdapter;
import hoainguyen.lib.recyclerhelper.recycler.XRecyclerView;
import hoainguyen.lib.recyclerhelper.recycler.item.RenderItem;
import hoainguyen.lib.recyclerhelper.recycler.item.indicator.LoadingIndicatorRenderItem;
import hoainguyen.lib.recyclerhelper.recycler.model.CollectionDataModel;

/**
 * Created by hoainguyen on 10/11/16.
 */
public abstract class BaseCollectionFragment<T> extends Fragment implements XRecyclerView.EndlessScrollListener {
    protected XRecyclerView mRecycleView;
    protected MultiTypeRVAdapter mDataAdapter;
    private boolean mLoading = false;
    protected CollectionDataModel<T> mDataCollection;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = getRootLayout(inflater, container);
        mRecycleView = (XRecyclerView) rootView.findViewById(R.id.recycle_view);
        mRecycleView.setEndlessScrollListener(this);
        mDataAdapter = new MultiTypeRVAdapter();
        mRecycleView.setAdapter(mDataAdapter);
        onCreateViewMore(inflater, rootView);
        return rootView;
    }

    public View getRootLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_base_collection_layout, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onLoadData();
    }

    protected abstract void onLoadData();

    public void appendDataCollection(CollectionDataModel<T> dataCollection) {
        if (mDataCollection != null) {
            mDataCollection.join(dataCollection);
            mDataAdapter.append(makeRenderItem(dataCollection.getData()));
        } else {
            refreshDataCollection(dataCollection);
        }
    }

    public void refreshDataCollection(CollectionDataModel<T> dataCollection) {
        mDataCollection = dataCollection;
        mDataAdapter.refresh(makeRenderItem(mDataCollection.getData()));
    }

    public abstract List<RenderItem> makeRenderItem(T[] data);

    public void onCreateViewMore(LayoutInflater inflater, View rootView) {

    }

    @Override
    public void onLoadMoreRequest() {
        if (!mLoading) {
            if (isCanLoadMore()) {
                mLoading = true;
                mDataAdapter.append(new LoadingIndicatorRenderItem());
                onLoadMore(mDataCollection.getPagination().getNextUrl());
            }
        }
    }

    @Override
    public void onLoadPreviousRequest() {
        if (!mLoading) {
            if (isCanLoadPrevious()) {
                mLoading = true;
                onLoadPrevious(mDataCollection.getPagination().getPreviousUrl());
            }
        }
    }

    public abstract void onLoadMore(String nextUrl);

    public abstract void onLoadPrevious(String previousUrl);

    private boolean isCanLoadPrevious() {
        return (mDataCollection != null && !mDataCollection.getPagination().getPreviousUrl().isEmpty());
    }

    private boolean isCanLoadMore() {
        return (mDataCollection != null && !mDataCollection.getPagination().getNextUrl().isEmpty());
    }

    public void onLoadMoreCompleted() {
        if (mLoading) {
            mDataAdapter.remove(mDataAdapter.getItemCount() - 1);
            mLoading = false;
        }
    }

    public void onLoadPreviousCompleted() {
        if (mLoading) {
            mLoading = false;
        }
    }
}
