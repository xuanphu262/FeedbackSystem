package android.mobile.feedbacksystem.ui.admin.report;

import android.mobile.feedbacksystem.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hoainguyen on 3/11/18.
 */

public class ReportFragment extends Fragment implements View.OnClickListener {
    private TextView mTvStartDay;
    private TextView mTvEndDay;
    private TextView mTvLocation;
    private TextView mBtnReport;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report, null);
        mTvStartDay = (TextView) rootView.findViewById(R.id.tv_startDay);
        mTvStartDay.setOnClickListener(this);
        mTvEndDay = (TextView) rootView.findViewById(R.id.tv_endDay);
        mTvStartDay.setOnClickListener(this);
        mTvLocation = (TextView) rootView.findViewById(R.id.tv_selectLocation);
        mTvStartDay.setOnClickListener(this);
        mBtnReport = (TextView) rootView.findViewById(R.id.btn_report);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_endDay:
                break;
            case R.id.tv_startDay:
                break;

        }
    }
}
