package hoainguyen.lib.recyclerhelper.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import hoainguyen.lib.recyclerhelper.Utils;

/**
 * Created by hoainguyen on 10/11/16.
 */
public class XRecyclerView extends RecyclerView {
    private static final int DEFAULT_SPAN_COUNT = 1;
    EndlessScrollListener mRecycleScrollListener;

    public XRecyclerView(Context context) {
        super(context);
        init();
    }

    public XRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setLayoutManager(new StaggeredGridLayoutManager(DEFAULT_SPAN_COUNT, VERTICAL));
        this.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                StaggeredGridLayoutManager staggeredGridLayoutManager = getLayoutManager();
                int totalItemCount = staggeredGridLayoutManager.getItemCount();
                if (dy > 0) {
                    int[] lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                    staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                    int lastVisibleItemPosition = Utils.findMaxValue(lastPositions);

                    if (totalItemCount == lastVisibleItemPosition + 1) {
                        if (mRecycleScrollListener != null)
                            mRecycleScrollListener.onLoadMoreRequest();
                    }
                } else {
                    int[] firstPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                    staggeredGridLayoutManager.findFirstVisibleItemPositions(firstPositions);
                    int firstVisibleItemPosition = Utils.findMinValue(firstPositions);

                    if (firstVisibleItemPosition < 4) {
                        if (mRecycleScrollListener != null)
                            mRecycleScrollListener.onLoadPreviousRequest();
                    }

                }
            }
        });
    }

    @Override
    public StaggeredGridLayoutManager getLayoutManager() {
        return (StaggeredGridLayoutManager) super.getLayoutManager();
    }

    public void relayout(int spanCount, int orientation) {
        getLayoutManager().setSpanCount(spanCount);
        getLayoutManager().setOrientation(orientation);
    }

    public void setEndlessScrollListener(EndlessScrollListener listener) {
        this.mRecycleScrollListener = listener;
    }

    public interface EndlessScrollListener {
        void onLoadMoreRequest();

        void onLoadPreviousRequest();
    }
}
