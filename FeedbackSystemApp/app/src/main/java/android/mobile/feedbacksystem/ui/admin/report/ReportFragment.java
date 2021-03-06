package android.mobile.feedbacksystem.ui.admin.report;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.AppConstant;
import android.mobile.feedbacksystem.ui.admin.SelectLocationActivity;
import android.mobile.feedbacksystem.ui.admin.result.ReportResultActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ReportFragment extends Fragment implements View.OnClickListener {
    private TextView mTvStartDay;
    private TextView mTvEndDay;
    private TextView mTvLocation;
    final SimpleDateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private Calendar mStartDay = Calendar.getInstance();
    private Calendar mEndDay = Calendar.getInstance();
    private String mLocationID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report, null);
        mTvStartDay = (TextView) rootView.findViewById(R.id.tv_startDay);
        mTvStartDay.setOnClickListener(this);
        mTvEndDay = (TextView) rootView.findViewById(R.id.tv_endDay);
        mTvEndDay.setOnClickListener(this);
        mTvLocation = (TextView) rootView.findViewById(R.id.tv_selectLocation);
        mTvLocation.setOnClickListener(this);
        rootView.findViewById(R.id.btn_report).setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_endDay:
                showDateTimePickerDialog(mEndDay, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mEndDay.set(year, month, dayOfMonth);
                        mTvEndDay.setText(DateFormat.format(mEndDay.getTime()));
                    }
                });
                break;
            case R.id.tv_startDay:
                showDateTimePickerDialog(mStartDay, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mStartDay.set(year, month, dayOfMonth);
                        mTvStartDay.setText(DateFormat.format(mStartDay.getTime()));
                    }
                });
                break;
            case R.id.btn_report:
                if (mTvStartDay.getText().toString().isEmpty()
                        || mTvEndDay.getText().toString().isEmpty()
                        || mTvLocation.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Input data error!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent reportResultIntent = new Intent(getContext(), ReportResultActivity.class);
                startActivity(reportResultIntent);
                break;
            case R.id.tv_selectLocation:
                Intent selectLocation = new Intent(getContext(), SelectLocationActivity.class);
                startActivityForResult(selectLocation, AppConstant.RQ_SELECT_LOCATION);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == AppConstant.RQ_SELECT_LOCATION) {
            mTvLocation.setText(data.getCharSequenceExtra(AppConstant.RESULT_LOCATION_NAME));
            mLocationID = data.getCharSequenceExtra(AppConstant.RESULT_LOCATION_ID).toString();
        }
    }

    private void showDateTimePickerDialog(Calendar currentDay, DatePickerDialog.OnDateSetListener listener) {
        DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(), listener,
                currentDay.get(Calendar.YEAR),
                currentDay.get(Calendar.MONTH),
                currentDay.get(Calendar.DAY_OF_MONTH));
        pickerDialog.show();
    }
}
