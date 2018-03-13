package android.mobile.feedbacksystem.ui;

import android.content.Intent;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.ui.admin.AdminMainActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                launchHomePage();
            }
        }, 2000);
    }

    private void launchHomePage() {
        Intent intent = new Intent(LauncherActivity.this, AdminMainActivity.class);
        startActivity(intent);
        finish();
    }

}
