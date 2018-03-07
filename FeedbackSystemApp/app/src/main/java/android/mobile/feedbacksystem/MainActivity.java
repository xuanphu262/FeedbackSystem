package android.mobile.feedbacksystem;

import android.content.Intent;
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
        }
    }

    @Override
    public void onBackPressed() {
    }
}
