package android.mobile.feedbacksystem.ui.admin;

import android.content.Intent;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.AppConstant;
import android.mobile.feedbacksystem.common.model.LocationModel;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import hoainguyen.lib.recyclerhelper.recycler.ExRecyclerView;

public class SelectLocationActivity extends AppCompatActivity {
    protected ExRecyclerView mRecyclerView;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onSelectLocation(LocationModel location) {
        Intent result = new Intent();
        result.putExtra(AppConstant.RESULT_LOCATION_NAME, location.getShortDescription());
        result.putExtra(AppConstant.RESULT_LOCATION_ID, location.getId());
        setResult(RESULT_OK, result);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);
        mRecyclerView = (ExRecyclerView) findViewById(hoainguyen.lib.recyclerhelper.R.id.recycle_view);
        mRecyclerView.appendAdapter(new SelectLocationAdapter());
        mRecyclerView.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
