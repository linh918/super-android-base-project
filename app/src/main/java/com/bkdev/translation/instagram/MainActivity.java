package com.bkdev.translation.instagram;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
    private InstagramHelper mInstagramHelper;
    @ViewById(R.id.btnConnect)
    Button btnConnect;
    static  final  String TAG="TAG7";
    private  CustomDialog mDialog;
    private String mAuthUrl;
    private InstagramUser mInstagramUser;



    @Override
    protected void init() {
        mInstagramHelper = new InstagramHelper(this, Constants.CLIENT_ID,
                Constants.CLIENT_SECRET, Constants.CALLBACK_URL);
        mInstagramHelper.setListener(new InstagramHelper.OAuthAuthenticationListener() {

            @Override
            public void onSuccess() {
                // tvSummary.setText("Connected as " + mInstagramHelper.getUserName());
                btnConnect.setText("Disconnect");
                mInstagramUser=mInstagramHelper.getInstagramUser();
                Log.i(TAG,mInstagramUser.getUsername()+" ");
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT)
                        .show();
            }
        });


        if (mInstagramHelper.hasAccessToken()) {
            // tvSummary.setText("Connected as " + mInstagramHelper.getUserName());
            btnConnect.setText("Disconnect");

        }

    }

    @Click(R.id.btnConnect)
    void onConnect(){
        connectOrDisconnectUser();
    }

    private void connectOrDisconnectUser() {
        if (mInstagramHelper.hasAccessToken()) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(
                    MainActivity.this);
            builder.setMessage("Disconnect from Instagram?")
                    .setCancelable(false)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    mInstagramHelper.resetAccessToken();
                                    // btnConnect.setVisibility(View.VISIBLE);
                                    btnConnect.setText("Connect");
                                    // tvSummary.setText("Not connected");
                                }
                            })
                    .setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            });
            final AlertDialog alert = builder.create();
            alert.show();
        } else {
            mInstagramHelper.authorize();
        }
    }





}
