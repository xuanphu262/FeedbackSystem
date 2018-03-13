package hoainguyen.lib.recyclerhelper.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import hoainguyen.lib.recyclerhelper.Utils;

/**
 * Created by hoainguyen on 3/28/17.
 */

public class ExRecyclerView extends RecyclerView {
    private static final int DEFAULT_SPAN_COUNT = 1;
    StackRecyclerAdapter mStackRecyclerAdapter;

    public ExRecyclerView(Context context) {
        super(context);
        init();
    }

    public ExRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mStackRecyclerAdapter = new StackRecyclerAdapter();
        setAdapter(mStackRecyclerAdapter);
        setLayoutManager(new StaggeredGridLayoutManager(DEFAULT_SPAN_COUNT, VERTICAL));
        this.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                StaggeredGridLayoutManager staggeredGridLayoutManager = getLayoutManager();
                if (dy > 0) {
                    int[] lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                    staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                    int lastVisibleItemPosition = Utils.findMaxValue(lastPositions);
                    mStackRecyclerAdapter.onPositionScrolled(dy, lastVisibleItemPosition);

                } else {
                    int[] firstPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                    staggeredGridLayoutManager.findFirstVisibleItemPositions(firstPositions);
                    int firstVisibleItemPosition = Utils.findMaxValue(firstPositions);
                    mStackRecyclerAdapter.onPositionScrolled(dy, firstVisibleItemPosition);
                }
            }
        });
    }

    @Override
    public StaggeredGridLayoutManager getLayoutManager() {
        return (StaggeredGridLayoutManager) super.getLayoutManager();
    }

    public void setSpanCount(int spanCount) {
        getLayoutManager().setSpanCount(spanCount);
    }

    public void setOrientation(int orientation) {
        getLayoutManager().setOrientation(orientation);
    }

    public void appendAdapter(DataSectionRVAdapter adapter) {
        mStackRecyclerAdapter.appendAdapter(adapter);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void start() {
        mStackRecyclerAdapter.start();
    }
}
