package com.bkdev.translation.videoview;

/**
 * Created by Brucetoo
 * On 2015/10/21
 * At 10:48
 */
public interface OnVideoGestureListener {
    /**
     * single tap controller view
     */
    void onSingleTap();

    /**
     * Horizontal scroll to control progress of video
     *
     * @param seekForward seek to forward or not
     */
    void onHorizontalScroll(boolean seekForward);

    /**
     * vertical scroll listen
     *
     * @param percent   swipe percent
     * @param direction left or right edge for control brightness or volume
     */
    void onVerticalScroll(float percent, int direction);
}
