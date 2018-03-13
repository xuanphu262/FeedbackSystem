package android.mobile.feedbacksystem.ui.admin.device;

import android.mobile.feedbacksystem.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hoainguyen.lib.recyclerhelper.collection.SectionCollectionFragment;

/**
 * Created by hoainguyen on 3/12/18.
 */

public class DeviceListFragment extends SectionCollectionFragment {

    @Override
    public View getRootLayout(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_device_list, null);

        return rootView;
    }

    @Override
    protected void onMakeAdapters() {
        mRecyclerView.appendAdapter(new DeviceAdapter());
    }
}
