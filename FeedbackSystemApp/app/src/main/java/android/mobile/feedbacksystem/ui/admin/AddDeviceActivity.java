package android.mobile.feedbacksystem.ui.admin;

import android.content.Intent;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.AppConstant;
import android.mobile.feedbacksystem.ui.qr.ScanQRCodeActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AddDeviceActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_scanQrCode).setOnClickListener(this);
        findViewById(R.id.btn_selectLocation).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_scanQrCode:
                Intent scanIntent = new Intent(AddDeviceActivity.this, ScanQRCodeActivity.class);
                startActivity(scanIntent);
                break;
            case R.id.btn_selectLocation:
                Intent selectLocation = new Intent(AddDeviceActivity.this, SelectLocationActivity.class);
                startActivityForResult(selectLocation, AppConstant.RQ_SELECT_LOCATION);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
