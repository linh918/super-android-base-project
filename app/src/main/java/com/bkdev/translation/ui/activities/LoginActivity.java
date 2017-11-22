package com.bkdev.translation.ui.activities;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.bkdev.translation.R;
import com.bkdev.translation.ui.BaseActivity;
import com.bkdev.translation.util.CustomTextureVideoView;
import com.bkdev.translation.videoview.CustomVideoController;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by congbi.nguyen on 7/29/2017.
 */
@EActivity(R.layout.activity_main)


public class LoginActivity extends BaseActivity implements CustomVideoController.MediaPlayerControlListener {
    @ViewById(R.id.videoView)
    CustomTextureVideoView mVideoview;
    @ViewById(R.id.videoSurfaceContainer)
    FrameLayout videoFrameLayout;

    @ViewById(R.id.loading)
    ProgressBar mProgressBarLoading;

    CustomVideoController mController;
    private boolean isStarted;

    @Override
    protected void init() {
        mController = new CustomVideoController.Builder(this, this)
                .withVideoTitle("Buck Bunny")
                .withVideoView(mVideoview)//to enable toggle display controller view
                .canControlBrightness(false)
                .canControlVolume(false)
                .canSeekVideo(false)
                .exitIcon(R.drawable.video_top_back)
                .pauseIcon(R.drawable.ic_media_pause)
                .playIcon(R.drawable.ic_media_play)
                .shrinkIcon(R.drawable.ic_media_fullscreen_shrink)
                .stretchIcon(R.drawable.ic_media_fullscreen_stretch)
                .build(videoFrameLayout);

        mProgressBarLoading.setVisibility(View.VISIBLE);
        mVideoview.setIsPrepared(true);
        mVideoview.setVideoPath("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        mVideoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mProgressBarLoading.setVisibility(View.GONE);
                mVideoview.start();
                isStarted = true;
            }
        });

    }

    @Override
    public void start() {
        mVideoview.start();
    }

    @Override
    public void pause() {
        Log.d("TAGG", "pause");
        mVideoview.pause();
    }

    @Override
    public int getDuration() {
        return mVideoview.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        Log.d("TAGG", " " + mVideoview.getCurrentPosition());
        return mVideoview.getCurrentPosition();
    }

    @Override
    public void seekTo(int position) {
        Log.d("TAGG", " " + position);
        mVideoview.seekTo(position);

    }

    @Override
    public boolean isPlaying() {
        return mVideoview.isPlaying();
    }

    @Override
    public boolean isComplete() {
        return mVideoview.getCurrentPosition() == mVideoview.getDuration();
    }

    @Override
    public int getBufferPercentage() {

        return mVideoview.getBufferPercentage();
    }

    @Override
    public boolean isFullScreen() {
        return false;
    }

    @Override
    public void toggleFullScreen() {

    }

    @Override
    public void exit() {
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        if (mController.isShowing()) {
            mController.onSingleTap();
        }
        if (null != mVideoview && mVideoview.isPrepared() && mVideoview.isPlaying()) {
            mVideoview.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (null != mVideoview && isStarted & !mVideoview.isPlaying()) {
            mVideoview.start();
        }
        super.onResume();
    }
}
