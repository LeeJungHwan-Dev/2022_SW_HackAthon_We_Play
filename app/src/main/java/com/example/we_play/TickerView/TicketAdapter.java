package com.example.we_play.TickerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.we_play.R;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.CustomViewHolder> {
    Context context;
    private ArrayList<String> thumbnail_url;
    private ArrayList<String> placeName;
    private ArrayList<String> placeLoc;
    private ArrayList<String> ticketDate;
    private ArrayList<String> ticketPrice;
    private ArrayList<String> ticketParticipant;

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
            this.ticket_price = (TextView) view.findViewById(R.id.tv_TicketPrice);
        }
    }


    public TicketAdapter(ArrayList<String> _thumbnail_url,
                         ArrayList<String> _placeName,
                         ArrayList<String> _placeLoc,
                         ArrayList<String> _ticketDate_,
                         ArrayList<String> _ticketPrice,
                         ArrayList<String> _ticketParticipant,
                         Context _context) {
        this.thumbnail_url = _thumbnail_url;
        this.placeName = _placeName;
        this.placeLoc = _placeLoc;
        this.ticketDate = _ticketDate_;
        this.ticketPrice = _ticketPrice;
        this.ticketParticipant = _ticketParticipant;
        this.context = _context;
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
        return (null != placeName ? placeName.size() : 0);
    }

}
