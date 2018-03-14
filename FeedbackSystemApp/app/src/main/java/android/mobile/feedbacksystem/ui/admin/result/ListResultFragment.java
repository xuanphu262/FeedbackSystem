package android.mobile.feedbacksystem.ui.admin.result;

import hoainguyen.lib.recyclerhelper.collection.SectionCollectionFragment;

public class ListResultFragment extends SectionCollectionFragment {
    @Override
    protected void onMakeAdapters() {
        mRecyclerView.appendAdapter(new FeedbackAdapter());
    }
}
