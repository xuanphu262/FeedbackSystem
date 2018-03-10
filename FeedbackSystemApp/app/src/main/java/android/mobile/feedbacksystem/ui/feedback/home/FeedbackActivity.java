package android.mobile.feedbacksystem.ui.feedback.home;

import android.graphics.Bitmap;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.AppUtils;
import android.mobile.feedbacksystem.common.QRCodeEncoder;
import android.mobile.feedbacksystem.ui.feedback.dialog.ThankDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        findViewById(R.id.btn_feedback_excellent).setOnClickListener(this);

        ImageView imgQRcode = findViewById(R.id.img_QRCode);
        String qrContent = AppUtils.getDeviceId(getApplicationContext());
        try {
            QRCodeEncoder qrEncoder = new QRCodeEncoder(BarcodeFormat.QR_CODE, qrContent, 200);
            Bitmap bitmap = qrEncoder.encodeAsBitmap();
            imgQRcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_feedback_excellent:
                ThankDialog thankDialog = new ThankDialog();
                thankDialog.show(getSupportFragmentManager(), "Thanks Dialog");
                break;
        }
    }
}
