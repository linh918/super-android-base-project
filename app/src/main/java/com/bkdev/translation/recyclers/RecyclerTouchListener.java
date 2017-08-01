package com.bkdev.translation.recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2/16/2017.
 */
public class RecyclerTouchListener implements  RecyclerView.OnItemTouchListener {
    private  ClickListener mClickListener;
    GestureDetector mGestureDetector;
    public RecyclerTouchListener(Context context, final RecyclerView recyclerView , ClickListener clickListener) {
        mClickListener=clickListener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {

                View child=recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(child!=null && mClickListener!=null){
                    mClickListener.onLongItemClick(child,recyclerView.getChildAdapterPosition(child));
                }


            }
        });


    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mClickListener != null && mGestureDetector.onTouchEvent(e)) {
            mClickListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
        }


        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public  interface ClickListener{
        public void onItemClick(View v, int position);
        public void onLongItemClick(View v, int position);

    }
}
