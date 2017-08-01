package com.bkdev.translation.ui.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import com.bkdev.translation.R;
import com.bkdev.translation.ui.BaseActivity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_bottom_nav)
public class BottomNavActivity extends BaseActivity {
    @ViewById
    TextView mTvMessage;
    @ViewById
    BottomNavigationView mBNVNavigation;
    @Extra
    String myMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTvMessage.setText(R.string.title_home+myMessage);
                    return true;
                case R.id.navigation_dashboard:
                    mTvMessage.setText(R.string.title_dashboard+myMessage);
                    return true;
                case R.id.navigation_notifications:
                    mTvMessage.setText(R.string.title_notifications+myMessage);
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void init() {
        mBNVNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
