package com.bkdev.translation.util;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by PhuocDH on 09/14/2017.
 */
public class CustomTextureVideoView extends TextureVideoView {
    private String mVideoPath;
    private boolean isPrepared;

    public CustomTextureVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextureVideoView(Context context) {
        super(context);
    }

    public CustomTextureVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setVideoPath(String path) {
        super.setVideoPath(path);
        mVideoPath = path;
    }

    public void setIsPrepared(boolean prepared) {
        isPrepared = prepared;
    }

    public boolean isPrepared() {
        return isPrepared;
    }

    public String getVideoPath() {
        return mVideoPath;
    }

}
