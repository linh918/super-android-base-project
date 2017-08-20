package com.bkdev.translation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bkdev.translation.R;
import com.bkdev.translation.ui.views.SquareImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by linh918 on 8/15/17.
 */

public class GridViewExampleAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private  List<String> mDatas;

    public GridViewExampleAdapter(@NonNull Context context, @NonNull List<String> datas) {
        super(context,0, datas);
        mContext=context;
        mDatas=datas;
    }

    private static class ViewHolder{
        SquareImageView image;

    }

    public void setmDatas(List<String> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid_view_example, parent, false);
            holder = new ViewHolder();
            holder.image = (SquareImageView) convertView.findViewById(R.id.gridImageView);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(mContext)
                .load(mDatas.get(position))
                .into(holder.image);
          return convertView;
    }
}
