package com.example.we_play.GridView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.we_play.R;

public class category_Adapter extends BaseAdapter {


    Context context;
    LayoutInflater inflater;
    String[] category_title = {"힐링","문화","액티비티","행사","로컬 맛집","가이드"};


    public category_Adapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return category_title[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = category_title[position];
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.custom_category, null);
        }

        TextView category_title_tv = convertView.findViewById(R.id.category_title);
        setimg(name,convertView);
        category_title_tv.setText(name);




        return convertView;
    }


    public void setimg(String str,View view){
        ImageView category_img_view = view.findViewById(R.id.category_img);

        if(str.equals("힐링")){
            Glide.with(view).load(R.drawable.healing).circleCrop().into(category_img_view);
        }else if(str.equals("문화")){
            Glide.with(view).load(R.drawable.culture).circleCrop().into(category_img_view);
        }else if(str.equals("로컬 맛집")){
            Glide.with(view).load(R.drawable.local_restaurant).circleCrop().into(category_img_view);
        }else if(str.equals("액티비티")){
            Glide.with(view).load(R.drawable.activities).circleCrop().into(category_img_view);
        }else if(str.equals("행사")){
            Glide.with(view).load(R.drawable.event).circleCrop().into(category_img_view);
        }else if(str.equals("가이드")){
            Glide.with(view).load(R.drawable.guide).circleCrop().into(category_img_view);
        }else{}
    }
}
