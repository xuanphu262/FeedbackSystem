package android.mobile.feedbacksystem.ui.admin;

import android.content.Intent;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.AppConstant;
import android.mobile.feedbacksystem.common.DataHelper;
import android.mobile.feedbacksystem.common.model.DeviceModel;
import android.mobile.feedbacksystem.ui.admin.event.UpdateEvent;
import android.mobile.feedbacksystem.ui.qr.ScanQRCodeActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

public class AddDeviceActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mEdtDeviceId;
    EditText mEdtDeviceName;
    TextView mTvLocation;
    private String mLocationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_scanQrCode).setOnClickListener(this);
        mTvLocation = (TextView) findViewById(R.id.tv_selectLocation);
        mTvLocation.setOnClickListener(this);
        findViewById(R.id.btn_create).setOnClickListener(this);

        mEdtDeviceId = (EditText) findViewById(R.id.edt_id);
        mEdtDeviceName = (EditText) findViewById(R.id.edt_deviceName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_scanQrCode:
                Intent scanIntent = new Intent(AddDeviceActivity.this, ScanQRCodeActivity.class);
                startActivityForResult(scanIntent, AppConstant.RQ_SCAN_QR_CODE);
                break;
            case R.id.tv_selectLocation:
                Intent selectLocation = new Intent(AddDeviceActivity.this, SelectLocationActivity.class);
                startActivityForResult(selectLocation, AppConstant.RQ_SELECT_LOCATION);
                break;
            case R.id.btn_create:
                if (mEdtDeviceId.getText().toString().isEmpty()
                        || mTvLocation.getText().toString().isEmpty()
                        || mEdtDeviceName.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Input data error!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String newDeviceId = DataHelper.getNewDeviceID();
                DataHelper.addDevice(new DeviceModel(newDeviceId, mEdtDeviceName.getText().toString(),
                        mEdtDeviceId.getText().toString(),
                        new Date(), mTvLocation.getText().toString()));
                EventBus.getDefault().post(UpdateEvent.DEVICE);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case AppConstant.RQ_SCAN_QR_CODE:
                    mEdtDeviceId.setText(data.getCharSequenceExtra(AppConstant.RESULT_DEVICE_ID));
                    break;
                case AppConstant.RQ_SELECT_LOCATION:
                    mTvLocation.setText(data.getCharSequenceExtra(AppConstant.RESULT_LOCATION_NAME));
                    mLocationID = data.getCharSequenceExtra(AppConstant.RESULT_LOCATION_ID).toString();
                    break;
            }
        }
    }
}
