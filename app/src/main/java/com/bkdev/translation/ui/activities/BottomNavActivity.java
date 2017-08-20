package com.bkdev.translation.ui.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.bkdev.translation.R;
import com.bkdev.translation.model.newsfeed.Instagram;
import com.bkdev.translation.model.newsfeed.NewsFeedResponse;
import com.bkdev.translation.model.newsfeed.Newsfeed;
import com.bkdev.translation.networks.core.ApiClient;
import com.bkdev.translation.networks.core.ApiConfig;
import com.bkdev.translation.networks.core.Callback;
import com.bkdev.translation.ui.BaseActivity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import retrofit2.Call;

@EActivity(R.layout.activity_bottom_nav)
public class BottomNavActivity extends BaseActivity {
    @ViewById
    TextView mTvMessage;
    @ViewById
    BottomNavigationView mBNVNavigation;
    @Extra
    String myMessage;
    List<Instagram> mNewsfeedList;

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

        Call<NewsFeedResponse> getNewsFeed=ApiClient.call().getNewsFeed(0,5);
        getNewsFeed.enqueue(new Callback<NewsFeedResponse>() {
            @Override
            public void success(NewsFeedResponse newsFeedResponse) {
                Log.i("Bottom","if"+newsFeedResponse.getStatus());
                if(newsFeedResponse.getStatus()==404){
                    mNewsfeedList.addAll(newsFeedResponse.getNewsfeedList());

                    Log.i("Bottom","if"+mNewsfeedList.size());
                }else{
                    Log.i("Bottom","else"+newsFeedResponse.getStatus());
                }
            }

            @Override
            public void failure(Error myError) {

            }
        });

    }
}
