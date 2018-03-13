package android.mobile.feedbacksystem.ui.feedback.dialog;

import android.graphics.Bitmap;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.AppUtils;
import android.mobile.feedbacksystem.common.QRCodeEncoder;
import android.mobile.feedbacksystem.ui.dialog.BaseDialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

public class QRCodeDialog extends BaseDialogFragment {
    ImageView imgQRcode;

    @Override
    public View initView(LayoutInflater inflater) {
        View rootView = inflater.inflate(R.layout.dialog_qr_code, null);
        imgQRcode = (ImageView) rootView.findViewById(R.id.img_QRCode);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String qrContent = AppUtils.getDeviceId(getContext());
        try {
            QRCodeEncoder qrEncoder = new QRCodeEncoder(BarcodeFormat.QR_CODE, qrContent, 200);
            Bitmap bitmap = qrEncoder.encodeAsBitmap();
            imgQRcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, 5000);
    }
}
