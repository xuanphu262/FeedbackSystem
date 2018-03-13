package android.mobile.feedbacksystem.ui.admin.device;

import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.ui.admin.event.NavigateEvent;
import android.mobile.feedbacksystem.ui.admin.event.UpdateEvent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import hoainguyen.lib.recyclerhelper.collection.SectionCollectionFragment;

/**
 * Created by hoainguyen on 3/12/18.
 */

public class DeviceListFragment extends SectionCollectionFragment {
    DeviceAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

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

    @Subscribe
    public void onGetEvent(UpdateEvent event) {
        if (event == UpdateEvent.DEVICE) {
            if (mAdapter != null)
                mAdapter.onStartLoadData();
        }
    }

    @Override
    protected void onMakeAdapters() {
        mAdapter = new DeviceAdapter();
        mRecyclerView.appendAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
