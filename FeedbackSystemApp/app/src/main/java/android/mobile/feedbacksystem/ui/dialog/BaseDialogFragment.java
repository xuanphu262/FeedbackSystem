package android.mobile.feedbacksystem.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by hoainguyen on 3/7/18.
 */

public abstract class BaseDialogFragment extends DialogFragment {
    protected Handler mHandler;
    private boolean mIsShown = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
    }

    @Override
    public void onStart() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
        super.onStart();
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        if (mIsShown) {
            return -1;
        } else {
            mIsShown = true;
            return super.show(transaction, tag);
        }
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (!mIsShown) {
            mIsShown = true;
            super.show(manager, tag);
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        mIsShown = false;
        super.onDismiss(dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        return initView(inflater);
    }

    public abstract View initView(LayoutInflater inflater);
}
