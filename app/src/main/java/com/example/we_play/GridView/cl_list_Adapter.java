package com.example.we_play.GridView;

import android.content.Context;
import android.graphics.Outline;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.we_play.R;

import java.util.ArrayList;

public class cl_list_Adapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> pic_arr = new ArrayList<>();
    ArrayList<String> location_arr = new ArrayList<>();

    public cl_list_Adapter(Context context , ArrayList<String> title,ArrayList<String> pic_arr,ArrayList<String> location_arr){
        this.context = context;
        this.title = title;
        this.pic_arr = pic_arr;
        this.location_arr = location_arr;
    }


    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int position) {
        return title.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = title.get(position);
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.view_page_list, null);
        }

        TextView category_title_tv = convertView.findViewById(R.id.title);
        ImageView category_image = convertView.findViewById(R.id.title_img);
        TextView category_location = convertView.findViewById(R.id.location);

        category_title_tv.setText(name);
        category_location.setText(location_arr.get(position));


        category_image.setClipToOutline(true);
        category_image.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 40);
            }
        });

        try {
            Glide.with(context).load(pic_arr.get(position)).thumbnail(0.1f).into(category_image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
