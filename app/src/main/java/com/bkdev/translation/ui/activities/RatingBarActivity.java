package com.bkdev.translation.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.bkdev.translation.R;
import com.bkdev.translation.ui.BaseActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Administrator on 9/10/2017.
 */
@EActivity(R.layout.activity_rating_bar)
public class RatingBarActivity extends BaseActivity implements ZXingScannerView.ResultHandler  {
    private static final String TAG = "TAGQR";
    private ZXingScannerView mScannerView;

    @ViewById(R.id.ratingBar)
    RatingBar mRatingBar;
    @ViewById(R.id.btnChechQRCode)
    Button mBtnCheckQRCode;
    @ViewById(R.id.imgQRCode)
    ImageView mImgQRCode;

    private boolean isScanning;
    public final static int QRcodeWidth = 500 ;

    @Override
    protected void init() {

        if(!isScanning){

            mRatingBar.setRating(3);
        }

    }

    @Click(R.id.btnChechQRCode)
    void clickCheckQRCode(){
        isScanning=true;
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Click(R.id.btnGenerateQRCode)
    void onClickgenerateQRCode(){
        try {
            Bitmap bitmap = TextToImageEncode(Base64Encode("123456 - 1 - dpb8TmywsUk:APA91bHIqFkmUWhVzQOONF63ZIxuDypKZ1MfZBjJpU6OD7pvY0aIf5owN6cLp2dtGn1kUavK8Fhzx5jp5xeJ3T6P5DSINTJypw85lHYqKKUsdhCtWlTYMRNRKRaYxSh3dsAEGGvOJTTM - 60 - kkk - 10% Discount on total bill"));

            mImgQRCode.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.QRCodeBlackColor):getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }



    @Override
    protected void onStop() {
        super.onStop();
        mScannerView.stopCamera();
        isScanning=false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
        isScanning=false;
    }





    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
      Toast.makeText(this,rawResult.getText(),Toast.LENGTH_SHORT).show(); // Prints scan results
        Toast.makeText(this, rawResult.getBarcodeFormat().toString(),Toast.LENGTH_SHORT).show();

        Toast.makeText(this, Base64Decode(rawResult.getText()),Toast.LENGTH_SHORT).show();
        Log.e(TAG,Base64Decode(rawResult.getText()));
        mScannerView.stopCamera();
        setContentView(R.layout.activity_rating_bar);
        isScanning=false;
        init();
    }

    public  static String Base64Decode(String converted){
        return  new String(Base64.decode(converted, Base64.DEFAULT));
    }

    public  static String Base64Encode(String toConvert){
        return  Base64.encodeToString(toConvert.toString().getBytes(), Base64.DEFAULT);
    }
}

