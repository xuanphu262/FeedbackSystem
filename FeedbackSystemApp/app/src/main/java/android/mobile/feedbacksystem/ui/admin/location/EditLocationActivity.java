package android.mobile.feedbacksystem.ui.admin.location;

import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.AppConstant;
import android.mobile.feedbacksystem.common.DataHelper;
import android.mobile.feedbacksystem.common.model.LocationModel;
import android.mobile.feedbacksystem.ui.admin.event.UpdateEvent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

public class EditLocationActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mEditShort;
    EditText mEditLong;
    LocationModel mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_location);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        mEditShort = (EditText) findViewById(R.id.edt_short);
        mEditLong = (EditText) findViewById(R.id.edt_long);

        Bundle bundle = getIntent().getExtras();
        mLocation = (LocationModel) bundle.getSerializable(AppConstant.KEY_LOCATION);

        updateUI(mLocation);
    }

    private void updateUI(LocationModel location) {
        mEditShort.setText(location.getShortDescription());
        mEditLong.setText(location.getLongDescription());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_update:
                if (mEditShort.getText().toString().isEmpty() || mEditShort.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Input data error", Toast.LENGTH_SHORT).show();
                    return;
                }

                mLocation.setShortDescription(mEditShort.getText().toString());
                mLocation.setLongDescription(mEditLong.getText().toString());
                DataHelper.updateLocation(mLocation);
                EventBus.getDefault().post(UpdateEvent.LOCATION);
                finish();
                break;
        }
    }
}
