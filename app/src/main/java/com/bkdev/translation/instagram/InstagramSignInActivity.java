package com.bkdev.translation.instagram;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bkdev.translation.R;
import com.bkdev.translation.model.newsfeed.Instagram;
import com.bkdev.translation.ui.BaseActivity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by VIT3-SV1 on 8/25/2017.
 */
@EActivity(R.layout.activity_instagram_sign_in)
public class InstagramSignInActivity extends BaseActivity {

    @ViewById(R.id.webView)
    WebView mWebView;
    private ProgressDialog mProgressDialog;
    private static final String TAG = "Instagram-WebView";

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void init() {

        setUpProgressDialog();
        setUpWebView();

        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
    }

    private void setUpProgressDialog(){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.setMessage("Loading...");
    }

    private void setUpWebView() {
        mWebView = new WebView(this);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setWebViewClient(new OAuthWebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
 //       mWebView.loadUrl(mUrl);


    }

    private class OAuthWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG, "Redirecting URL " + url);

            if (url.startsWith(InstagramApp.mCallbackUrl)) {
                String urls[] = url.split("=");
                Bundle bundle = new Bundle();

            //    ((OAuthDialogListener)mActivity ).onComplete(urls[1]);
            //    InstagramDialog.this.dismiss();
                finish();
                return true;
            }
            return false;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            Log.d(TAG, "Page error: " + description);

            super.onReceivedError(view, errorCode, description, failingUrl);
          //  mListener.onError(description);
          //  InstagramDialog.this.dismiss();
            finish();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.d(TAG, "Loading URL: " + url);

            super.onPageStarted(view, url, favicon);
            mProgressDialog.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            String title = mWebView.getTitle();
            Log.d(TAG, "onPageFinished URL: " + url);
            mProgressDialog.dismiss();
        }

    }

    public interface OAuthDialogListener {
        public abstract void onComplete(String accessToken);
        public abstract void onError(String error);
    }
}


