package android.mobile.feedbacksystem.ui.qr;

import android.graphics.Bitmap;
import android.mobile.feedbacksystem.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

public class QRCodeActivity extends AppCompatActivity {
    ImageView mImgQRcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        mImgQRcode = findViewById(R.id.img_qr);

        String qrContent = "http://vnexpress.net";
        try {
            QRCodeEncoder qrEncoder = new QRCodeEncoder(BarcodeFormat.QR_CODE, qrContent, 200);
            Bitmap bitmap = qrEncoder.encodeAsBitmap();
            mImgQRcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
