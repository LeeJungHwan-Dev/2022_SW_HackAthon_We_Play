package com.example.we_play;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.we_play.GridView.category_Adapter;

public class Category_page extends AppCompatActivity {

    String big_city = "";
    TextView citiy_title;
    GridView category_view;
    ImageButton back_city;
    ImageButton set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);

        citiy_title = findViewById(R.id.citiy_title);
        category_view = findViewById(R.id.category_grid);
        back_city = findViewById(R.id.button4);
        set = findViewById(R.id.my_setting_btn);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),infoActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        });


        getCityData();

        citiy_title.setText(big_city);

        category_Adapter category_adapter = new category_Adapter(this);
        category_view.setAdapter(category_adapter);

        category_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getAdapter().getItem(position).toString().equals("로컬 맛집")){
                    Intent intent = new Intent(getApplicationContext(),food_page.class);
                    intent.putExtra("도시",big_city);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                }else {
                    Intent intent = new Intent(getApplicationContext(), Category_list.class);
                    intent.putExtra("지역", big_city);
                    intent.putExtra("카테고리", parent.getAdapter().getItem(position).toString());
                    startActivity(intent);
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                }
            }
        });

        back_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TravelActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                finish();
            }
        });
    }

    public void getCityData(){
        Intent intent = getIntent();
        big_city = intent.getStringExtra("big_city");
    }

}