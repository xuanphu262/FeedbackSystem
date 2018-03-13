package android.mobile.feedbacksystem.ui.admin;

import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.DataHelper;
import android.mobile.feedbacksystem.common.model.LocationModel;
import android.mobile.feedbacksystem.ui.admin.event.UpdateEvent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.Date;

public class AddLocationActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mEditShort;
    EditText mEditLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_addLocation).setOnClickListener(this);
        mEditShort = (EditText) findViewById(R.id.edt_short);
        mEditLong = (EditText) findViewById(R.id.edt_long);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_addLocation:
                if (mEditShort.getText().toString().isEmpty() || mEditShort.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Input data error", Toast.LENGTH_SHORT).show();
                    return;
                }

                String exampleId = (DataHelper.locationList.size() + 1) +"";
                DataHelper.addLocation(new LocationModel(exampleId, mEditShort.getText().toString(),
                        mEditLong.getText().toString(), new Date()));
                EventBus.getDefault().post(UpdateEvent.LOCATION);
                finish();
                break;
        }
    }
}
