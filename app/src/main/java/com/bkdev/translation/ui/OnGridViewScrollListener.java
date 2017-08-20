package com.bkdev.translation.ui;

import android.widget.AbsListView;

/**
 * Created by linh918 on 8/15/17.
 */


    public abstract class OnGridViewScrollListener implements AbsListView.OnScrollListener{

        @Override
        public void onScrollStateChanged(AbsListView absListView, int i) {
            OnScrollStateChanged(absListView,i);
        }

        @Override
        public void onScroll(AbsListView absListView, int i, int i1, int i2) {
            OnScroll(absListView,i,i1,i2);
        }

        public  abstract  void OnScrollStateChanged(AbsListView absListView, int i);
        public abstract  void OnScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount,
                                       int totalItemCount);
}

