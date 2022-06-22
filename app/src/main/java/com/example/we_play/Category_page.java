package com.example.we_play;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.we_play.GridView.category_Adapter;
import com.example.we_play.GridView.city_Adapter;

public class Category_page extends AppCompatActivity {

    String big_city = "";
    String small_city = "";
    TextView citiy_title;
    GridView category_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);

        citiy_title = findViewById(R.id.citiy_title);
        category_view = findViewById(R.id.category_grid);

        getCityData();

        citiy_title.setText(big_city + " " + small_city);

        category_Adapter category_adapter = new category_Adapter(this);
        category_view.setAdapter(category_adapter);

    }

    public void getCityData(){
        Intent intent = getIntent();
        big_city = intent.getStringExtra("big_city");
        small_city = intent.getStringExtra("small_city");
    }

}