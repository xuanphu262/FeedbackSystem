package android.mobile.feedbacksystem.ui.feedback.dialog;

import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.ui.dialog.BaseDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by hoainguyen on 3/13/18.
 */

public class SubmitMoreDialog extends BaseDialogFragment {
    @Override
    public View initView(LayoutInflater inflater) {
        View rootView = inflater.inflate(R.layout.dialog_submit_more_dialog, null);
        return rootView;
    }
}
