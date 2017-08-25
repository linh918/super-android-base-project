package com.bkdev.translation.instagram;

import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bkdev.translation.R;
import com.bkdev.translation.ui.BaseActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by VIT3-SV1 on 8/25/2017.
 */
@EActivity(R.layout.activity_main_instagram)
public class MainActivity extends  BaseActivity   {
    @ViewById(R.id.btnConnect)
    Button btnConnect;
    static  final  String TAG="TAG7";
    private  CustomDialog mDialog;
    private String mAuthUrl;


    @Override
    protected void init() {

        mAuthUrl="https://account.box.com/login";
        CustomDialog.CustomDiaLogListener listener = new CustomDialog.CustomDiaLogListener() {
            @Override
            public void onComplete(String code) {
                Log.i(TAG,code);
            }

            @Override
            public void onError(String error) {
                Log.i(TAG,error);
            }
        };

        mDialog = new CustomDialog(this, mAuthUrl, listener);
    }

    @Click(R.id.btnConnect)
    void onConnect(){
        mDialog.show();
    }




}
