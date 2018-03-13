package android.mobile.feedbacksystem.ui.admin.location;

import android.mobile.feedbacksystem.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hoainguyen.lib.recyclerhelper.collection.SectionCollectionFragment;

/**
 * Created by hoainguyen on 3/11/18.
 */

public class LocationListFragment extends SectionCollectionFragment {

    @Override
    public View getRootLayout(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_location_list, null);
        return rootView;
    }

    @Override
    protected void onMakeAdapters() {
        mRecyclerView.appendAdapter(new LocationAdapter());
    }
}
