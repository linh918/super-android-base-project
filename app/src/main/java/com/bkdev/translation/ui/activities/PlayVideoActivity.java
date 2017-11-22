package com.bkdev.translation.ui.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.bkdev.translation.R;
import com.bkdev.translation.ui.BaseActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Administrator on 11/17/2017.
 */
@EActivity(R.layout.activity_play_video)
public class PlayVideoActivity extends BaseActivity {
    @ViewById(R.id.btnShareComponent)
    Button btnTest;

    @Override
    protected void init() {


    }

    @Click(R.id.btnShareComponent)
    void onClickTest() {
        Intent i = new Intent(PlayVideoActivity.this, LoginActivity_.class);

        View sharedView = btnTest;
        String transitionName = getString(R.string.blue_name);

        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(PlayVideoActivity.this, sharedView, transitionName);
        startActivity(i, transitionActivityOptions.toBundle());
    }
}
