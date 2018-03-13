package android.mobile.feedbacksystem.ui.feedback.home;

import android.mobile.feedbacksystem.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SubmitReasonActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_reason);

        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_submit).setOnClickListener(this);
        findViewById(R.id.btn_wetFloor).setOnClickListener(this);
        findViewById(R.id.btn_binFull).setOnClickListener(this);
        findViewById(R.id.btn_noPaper).setOnClickListener(this);
        findViewById(R.id.btn_dirtyToilet).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        setResult(RESULT_OK);
        finish();
    }
}
