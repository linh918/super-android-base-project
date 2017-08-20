package com.bkdev.translation.ui.activities;

import android.util.Log;
import android.widget.AbsListView;
import android.widget.GridView;

import com.bkdev.translation.R;
import com.bkdev.translation.adapters.GridViewExampleAdapter;
import com.bkdev.translation.ui.BaseActivity;
import com.bkdev.translation.ui.OnGridViewScrollListener;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linh918 on 8/15/17.
 */
@EActivity(R.layout.activity_grid_view_example)
public class GridViewExampleActivity extends BaseActivity {
    @ViewById(R.id.gridView)
    GridView mGridView;
    List<String> datas;
    List<String> mAdapterData;
    private GridViewExampleAdapter mAdapter;

    @Override
    protected void init() {
         datas = new ArrayList<>();

        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        mAdapter= new GridViewExampleAdapter(this,datas);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnScrollListener(new OnGridViewScrollListener() {
            @Override
            public void OnScrollStateChanged(AbsListView absListView, int i) {
                Log.i("TAG7","scroll");
            }

            @Override
            public void OnScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int lastItem = firstVisibleItem+visibleItemCount;
                Log.i("TAG7",lastItem+" "+visibleItemCount+" "+firstVisibleItem+" "+totalItemCount);
                if(totalItemCount<=firstVisibleItem+visibleItemCount){
                    loadMore();

                }
            }
        });
    }

    public  void loadMore(){
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        datas.add("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        mAdapter.setmDatas(datas);
    }
}
