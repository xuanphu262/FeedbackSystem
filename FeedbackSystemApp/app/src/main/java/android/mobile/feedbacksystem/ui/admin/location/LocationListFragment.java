package android.mobile.feedbacksystem.ui.admin.location;

import android.content.Intent;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.AppConstant;
import android.mobile.feedbacksystem.ui.admin.event.EditLocationEvent;
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
 * Created by hoainguyen on 3/11/18.
 */

public class LocationListFragment extends SectionCollectionFragment {

    LocationAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View getRootLayout(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_location_list, null);
        rootView.findViewById(R.id.btn_addLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(NavigateEvent.ADD_LOCATION);
            }
        });
        return rootView;
    }

    @Override
    protected void onMakeAdapters() {
        mAdapter = new LocationAdapter();
        mRecyclerView.appendAdapter(mAdapter);
    }

    @Subscribe
    public void onGetEvent(UpdateEvent event) {
        if (event == UpdateEvent.LOCATION) {
            if (mAdapter != null)
                mAdapter.onStartLoadData();
        }
    }

    @Subscribe
    public void onEditEvent(EditLocationEvent event) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstant.KEY_LOCATION, event.getLocation());
        Intent addDeviceIntent = new Intent(getActivity(), EditLocationActivity.class);
        addDeviceIntent.putExtras(bundle);
        startActivity(addDeviceIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
