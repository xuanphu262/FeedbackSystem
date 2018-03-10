package android.mobile.feedbacksystem;

import android.content.Intent;
import android.mobile.feedbacksystem.ui.qr.QRCodeActivity;
import android.mobile.feedbacksystem.ui.qr.ScanQRCodeActivity;
import android.mobile.feedbacksystem.ui.report.ReportActivity;
import android.mobile.feedbacksystem.ui.setup.SetupActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_report).setOnClickListener(this);
        findViewById(R.id.btn_setup).setOnClickListener(this);
        findViewById(R.id.btn_scan).setOnClickListener(this);
        findViewById(R.id.btn_showQR).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_report:
                Intent reportIntent = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(reportIntent);
                break;
            case R.id.btn_setup:
                Intent setUpIntent = new Intent(MainActivity.this, SetupActivity.class);
                startActivity(setUpIntent);
                break;
            case R.id.btn_scan:
                Intent scanIntent = new Intent(MainActivity.this, ScanQRCodeActivity.class);
                startActivity(scanIntent);
                break;
            case R.id.btn_showQR:
                Intent showQRIntent = new Intent(MainActivity.this, QRCodeActivity.class);
                startActivity(showQRIntent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
    }
}
