package com.example.we_play.GridView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.we_play.R;

public class city_Adapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    String[] city;

    public city_Adapter(Context context , String[] city ){
        this.context = context;
        this.city = city;
    }


    @Override
    public int getCount() {
        return city.length;
    }

    @Override
    public Object getItem(int position) {
        return city[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.gird_layout, null);
        }

        TextView city_word = convertView.findViewById(R.id.citiy_tv);
        city_word.setText(city[position]);

        return convertView;
    }
}
