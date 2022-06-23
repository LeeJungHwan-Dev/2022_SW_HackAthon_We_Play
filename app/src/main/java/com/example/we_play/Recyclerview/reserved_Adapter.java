package com.example.we_play.Recyclerview;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Dictionary;

import com.example.we_play.R;

public class reserved_Adapter extends RecyclerView.Adapter<reserved_Adapter.CustomViewHolder> {
    private ArrayList<Dictionary> mList;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView thumbnail;
        protected TextView placeName;
        protected TextView description;


        public CustomViewHolder(View view) {
            super(view);
            this.thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            this.placeName = (TextView) view.findViewById(R.id.placeName);
            this.description = (TextView) view.findViewById(R.id.placeDescription);
        }
    }


    public reserved_Adapter(ArrayList<Dictionary> list) {
        this.mList = list;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_reserved, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

//        viewholder.thumbnail.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.placeName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.description.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        viewholder.placeName.setGravity(Gravity.CENTER);
        viewholder.description.setGravity(Gravity.CENTER);



//        viewholder.id.setText(mList.get(position).getId());
//        viewholder.placeName.setText(mList.get(position).getKorean());
//        viewholder.description.setText(mList.get(position).getKorean());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}
