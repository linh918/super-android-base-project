package com.bkdev.translation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bkdev.translation.R;
import com.bkdev.translation.model.person.Person;
import com.bkdev.translation.ui.BaseAdapter;
import com.bkdev.translation.ui.BaseHolder;

import java.util.List;

/**
 * Created by VIT3-SV1 on 7/31/2017.
 */

public class PersonRecyclerAdapter extends RecyclerView.Adapter<PersonRecyclerAdapter.MyViewHolder> {
    private List<Person> mPersons;
    private Context mContext;
    public PersonRecyclerAdapter(@NonNull Context context, @NonNull List<Person> persons) {
        this.mPersons=persons;
        this.mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_person,parent,false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTvName.setText(mPersons.get(position).getName());
        holder.mTvClassName.setText(mPersons.get(position).getClassName());

    }

    @Override
    public int getItemCount() {
        return (mPersons==null? 0:mPersons.size() );
    }

    class MyViewHolder extends BaseHolder{
        private  final TextView mTvName;
        private  final  TextView mTvClassName;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTvName=(TextView)itemView.findViewById(R.id.mTvName);
            mTvClassName=(TextView)itemView.findViewById(R.id.mTvClassName);

        }

        @Override
        public void onSingleClick(View v) {

        }
    }

}
