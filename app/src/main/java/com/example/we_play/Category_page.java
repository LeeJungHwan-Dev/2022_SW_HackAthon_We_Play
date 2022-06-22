package com.example.we_play;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Category_page extends AppCompatActivity {

    String big_city = "";
    String small_city = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);

        getCityData();

        Toast.makeText(this,big_city + small_city, Toast.LENGTH_SHORT).show();

    }

    public void getCityData(){
        Intent intent = getIntent();
        big_city = intent.getStringExtra("big_city");
        small_city = intent.getStringExtra("small_city");
    }

}