package android.mobile.feedbacksystem.ui.admin.device;

import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.ui.admin.event.NavigateEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import hoainguyen.lib.recyclerhelper.collection.SectionCollectionFragment;

/**
 * Created by hoainguyen on 3/12/18.
 */

public class DeviceListFragment extends SectionCollectionFragment {

    @Override
    public View getRootLayout(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_device_list, null);
        rootView.findViewById(R.id.btn_addDevice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(NavigateEvent.ADD_DEVICE);
            }
        });
        return rootView;
    }

    @Override
    protected void onMakeAdapters() {
        mRecyclerView.appendAdapter(new DeviceAdapter());
    }
}
