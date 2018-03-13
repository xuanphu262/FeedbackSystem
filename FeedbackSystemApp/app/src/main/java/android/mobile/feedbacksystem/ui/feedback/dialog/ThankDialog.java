package android.mobile.feedbacksystem.ui.feedback.dialog;

import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.ui.dialog.BaseDialogFragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

public class ThankDialog extends BaseDialogFragment {
    @Override
    public View initView(LayoutInflater inflater) {
        View rootView = inflater.inflate(R.layout.dialog_thanks, null);
        rootView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, 3000);
        return rootView;
    }
}
