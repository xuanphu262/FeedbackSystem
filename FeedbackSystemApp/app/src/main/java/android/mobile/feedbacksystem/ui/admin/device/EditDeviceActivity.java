package android.mobile.feedbacksystem.ui.admin.device;

import android.content.Intent;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.AppConstant;
import android.mobile.feedbacksystem.common.DataHelper;
import android.mobile.feedbacksystem.common.model.DeviceModel;
import android.mobile.feedbacksystem.ui.admin.SelectLocationActivity;
import android.mobile.feedbacksystem.ui.admin.event.UpdateEvent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

public class EditDeviceActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mTvDeviceId;
    EditText mEdtDeviceName;
    TextView mTvLocation;

    DeviceModel mDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_device);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);

        mTvLocation = (TextView) findViewById(R.id.tv_selectLocation);
        mTvLocation.setOnClickListener(this);

        mTvDeviceId = (TextView) findViewById(R.id.tv_id);
        mEdtDeviceName = (EditText) findViewById(R.id.edt_deviceName);

        Bundle bundle = getIntent().getExtras();
        mDevice = (DeviceModel) bundle.getSerializable(AppConstant.KEY_DEVICE);

        updateUI(mDevice);
    }

    private void updateUI(DeviceModel device) {
        mTvLocation.setText(DataHelper.getLocation(device.getLocationId()).getShortDescription());
        mTvDeviceId.setText(device.getUuid());
        mEdtDeviceName.setText(device.getDeviceName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.tv_selectLocation:
                Intent selectLocation = new Intent(EditDeviceActivity.this, SelectLocationActivity.class);
                startActivityForResult(selectLocation, AppConstant.RQ_SELECT_LOCATION);
                break;
            case R.id.btn_update:
                if (mTvDeviceId.getText().toString().isEmpty()
                        || mTvLocation.getText().toString().isEmpty()
                        || mEdtDeviceName.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Input data error!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mDevice.setDeviceName(mEdtDeviceName.getText().toString());
                DataHelper.updateDevice(mDevice);
                EventBus.getDefault().post(UpdateEvent.DEVICE);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == AppConstant.RQ_SELECT_LOCATION) {
            mTvLocation.setText(data.getCharSequenceExtra(AppConstant.RESULT_LOCATION_NAME));
            mDevice.setLocationId(data.getCharSequenceExtra(AppConstant.RESULT_LOCATION_ID).toString());
        }
    }
}
