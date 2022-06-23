package com.example.we_play;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.we_play.ListView.reserved_Adapter;
import com.example.we_play.ListView.reserved_data;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Reserved_page extends AppCompatActivity {

    ArrayList<reserved_data> reservedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.InitializeMovieData();
        ListView listview = (ListView)findViewById(R.id.listview_reserved) ;
        final reserved_Adapter ListviewAdapter = new reserved_Adapter(this,reservedList);

        listview.setAdapter(ListviewAdapter);

        // 코드 계속...
    }

    public void InitializeMovieData()
    {
        reservedList = new ArrayList<reserved_data>();

        reservedList.add(new reserved_data(R.drawable.logo, "미션임파서블","15세 이상관람가"));
        reservedList.add(new reserved_data(R.drawable.logo, "아저씨","19세 이상관람가"));
        reservedList.add(new reserved_data(R.drawable.logo, "어벤져스","12세 이상관람가"));
    }
}
