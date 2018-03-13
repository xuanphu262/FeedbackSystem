package android.mobile.feedbacksystem.ui.admin.location;

import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.ui.admin.event.NavigateEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import hoainguyen.lib.recyclerhelper.collection.SectionCollectionFragment;

/**
 * Created by hoainguyen on 3/11/18.
 */

public class LocationListFragment extends SectionCollectionFragment {

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
        mRecyclerView.appendAdapter(new LocationAdapter());
    }
}
