package com.example.we_play;

import androidx.appcompat.app.AppCompatActivity;

import com.example.we_play.GridView.city_Adapter;
import com.example.we_play.Module.location_return;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class TravelActivity extends AppCompatActivity {


    GridView city_chose; // 2 * 2로 리스트를 보여주기 위한 그리드 뷰
    location_return location_return = new location_return(); // 지역명을 불러오기 위한 클래스 객체
    String Big_city = ""; // 도단위 저장 변수
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Intent mintent = getIntent();

        city_chose = findViewById(R.id.city_chose);
        back = findViewById(R.id.main_page_back_btn);

        /**
         *
         * 그리드뷰를 사용하여 2*2를 구현하였습니다.
         * baseAdapter를 상속받아 city_Adapter를 구현하였습니다
         *
         * */

        city_Adapter city_adapter = new city_Adapter(this,location_return.Big_city());
        city_chose.setAdapter(city_adapter);


        city_chose.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setLocation(parent.getAdapter().getItem(position).toString()); // 시 단위로 그리드 뷰 변경을 위한 함수 호출
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                intent.putExtra("아이디", mintent.getStringExtra("아이디"));
                intent.putExtra("이름", mintent.getStringExtra("이름"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                finish();
            }
        });
    }



    public void setLocation(String city){
        Big_city = city;
        Intent intent = new Intent(getApplicationContext(),Category_page.class);
        intent.putExtra("big_city",Big_city); // 도 단위 자료 내장후 넘김
        startActivity(intent);
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);

    }

    public void reset_city(){
        Big_city = "";
        city_Adapter city_adapter = new city_Adapter(this,location_return.Big_city());
        city_adapter.notifyDataSetChanged(); // 데이터 변경을 암시 하고 아래 코드로 어댑터 재설정
        city_chose.setAdapter(city_adapter);
    }



    @Override
    public void onBackPressed() {
       // super.onBackPressed();

    }
}