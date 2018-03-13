package hoainguyen.lib.recyclerhelper.recycler;

import java.util.List;

import hoainguyen.lib.recyclerhelper.recycler.item.RenderItem;
import hoainguyen.lib.recyclerhelper.recycler.item.indicator.LoadingIndicatorRenderItem;
import hoainguyen.lib.recyclerhelper.recycler.model.CollectionDataModel;
import hoainguyen.lib.recyclerhelper.recycler.model.PaginationModel;

/**
 * Created by hoainguyen on 3/28/17.
 */

public abstract class ExpandableDataSectionRVAdapter<T> extends DataSectionRVAdapter {
    protected CollectionDataModel<T> mDataCollection;

    @Override
    public void onPositionScrolled(int dy, int position) {
        if (dy >= 0) {
            if (position > getItemCount() - 4)
                onExpandDownRequest();
        } else {
            if (position < 4)
                onExpandUpRequest();
        }
    }

    private void onExpandUpRequest() {
        if (!isLoading()) {
            if (canExpandUp()) {
                setLoadingState(true);
                insert(0, new LoadingIndicatorRenderItem());
                onExpandUp(mDataCollection.getPagination());
            }
        }
    }

    private boolean canExpandUp() {
        return (mDataCollection != null && !mDataCollection.getPagination().getPreviousUrl().isEmpty());
    }

    private void onExpandDownRequest() {
        if (!isLoading()) {
            if (canExpandDown()) {
                setLoadingState(true);
                append(new LoadingIndicatorRenderItem());
                onExpandDown(mDataCollection.getPagination());
            }
        }
    }

    private boolean canExpandDown() {
        return (mDataCollection != null && !mDataCollection.getPagination().getNextUrl().isEmpty());
    }

    public abstract void onExpandDown(PaginationModel pagination);

    public abstract void onExpandUp(PaginationModel paginationModel);

    public void appendDownDataCollection(CollectionDataModel<T> dataCollection) {
        if (mDataCollection != null) {
            mDataCollection.join(dataCollection);
            append(makeRenderItem(dataCollection.getData()));
        } else {
            refreshDataCollection(dataCollection);
        }
    }

    public void appendUpDataCollection(CollectionDataModel<T> dataCollection) {
        if (mDataCollection != null) {
            mDataCollection.join(dataCollection);
            insert(0, makeRenderItem(dataCollection.getData()));
        } else {
            refreshDataCollection(dataCollection);
        }
    }

    public void refreshDataCollection(CollectionDataModel<T> dataCollection) {
        mDataCollection = dataCollection;
        refresh(makeRenderItem(mDataCollection.getData()));
    }

    public abstract List<RenderItem> makeRenderItem(T[] data);

    public void onLoadExpandDownDataCompleted() {
        if (isLoading()) {
            remove(getItemCount() - 1);
            setLoadingState(false);
        }
    }

    public void onLoadDataCompleted() {
        if (isLoading()) {
            setLoadingState(false);
        }
    }

    public void onLoadExpandUpDataCompleted() {
        if (isLoading()) {
            remove(0);
            setLoadingState(false);
        }
    }
}
