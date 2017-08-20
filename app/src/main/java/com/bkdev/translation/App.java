package com.bkdev.translation;

import android.app.Application;

import com.bkdev.translation.networks.core.ApiClient;
import com.bkdev.translation.networks.core.ApiConfig;

import org.androidannotations.annotations.EApplication;

/**
 * Created by linh918 on 8/12/17.
 */
@EApplication
public class App  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        // Setup Api client
        ApiConfig apiConfig = ApiConfig.builder(getApplicationContext())
                .baseUrl("http://dev.hoiandigitalguide.com")
                .setAgent(true)
                .setAuth(true)
                .build();
        ApiClient.getInstance().init(apiConfig);
    }
}
