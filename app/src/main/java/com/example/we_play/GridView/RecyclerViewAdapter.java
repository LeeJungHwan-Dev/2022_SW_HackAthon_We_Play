package com.example.we_play.GridView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.we_play.Info_page;
import com.example.we_play.R;
import com.willy.ratingbar.BaseRatingBar;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<String> img_link = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> location = new ArrayList<>();
    ArrayList<String> rating = new ArrayList<>();
    String big_city = "";
    String category = "";


    public RecyclerViewAdapter(ArrayList<String> img_link , ArrayList<String> title, ArrayList<String> location, Context context, ArrayList<String> rating,String big_city , String category){
        this.img_link = img_link;
        this.title = title;
        this.location = location;
        this.context = context;
        this.rating = rating;
        this.big_city = big_city;
        this.category = category;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.custom_food_layout,parent,false);
        RecyclerViewAdapter.ViewHolder vh = new RecyclerViewAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {


        holder.food_title.setText(title.get(position));
        holder.food_location.setText(location.get(position));
        BaseRatingBar scaleRatingBar = holder.itemView.findViewById(R.id.rating);
        scaleRatingBar.setRating(Float.parseFloat(rating.get(position)));
        Glide.with(context).load(img_link.get(position)).circleCrop().into(holder.food_image);

        scaleRatingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        holder.food_info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Info_page.class);
                intent.putExtra("시티",big_city);
                intent.putExtra("카테고리",category);
                intent.putExtra("위치",location.get(position));
                intent.putExtra("사진",img_link.get(position));
                intent.putExtra("타이틀",title.get(position));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView food_image;
        TextView food_title, food_location;
        ImageButton food_info_btn;


        ViewHolder(View itemView) {
            super(itemView);

            food_image = itemView.findViewById(R.id.food_img);
            food_title = itemView.findViewById(R.id.food_title);
            food_location = itemView.findViewById(R.id.food_location_tv);
            food_info_btn = itemView.findViewById(R.id.more_info_btn);


        }
    }

}
