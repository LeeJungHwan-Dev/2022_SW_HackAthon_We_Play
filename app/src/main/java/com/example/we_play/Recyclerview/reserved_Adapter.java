package com.example.we_play.Recyclerview;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        protected ImageView thumbnail_url;
        protected TextView place;
        protected TextView location;
        protected TextView ticket_date;
        protected TextView ticket_participant;
        protected TextView ticket_price;

        public CustomViewHolder(View view) {
            super(view);
            this.thumbnail_url = (ImageView) view.findViewById(R.id.thumbnail);
            this.place = (TextView) view.findViewById(R.id.tv_placeName);
            this.location = (TextView) view.findViewById(R.id.tv_placeLocation);
            this.ticket_date = (TextView) view.findViewById(R.id.tv_ticketDate);
            this.ticket_participant = (TextView) view.findViewById(R.id.tv_TicketPrice);
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

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}
