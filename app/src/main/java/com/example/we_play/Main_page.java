package com.example.we_play;

import androidx.appcompat.app.AppCompatActivity;

import com.example.we_play.GridView.city_Adapter;
import com.example.we_play.Module.location_return;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class Main_page extends AppCompatActivity {


    GridView city_chose; // 2 * 2로 리스트를 보여주기 위한 그리드 뷰
    location_return location_return = new location_return(); // 지역명을 불러오기 위한 클래스 객체
    Boolean check = false; // 처음 도를 선택했는지 시를 선택했는지 확인하는 변수
    String Big_city = ""; // 도단위 저장 변수
    String small_city = ""; // 시단위 저장 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        city_chose = findViewById(R.id.city_chose);


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
                if (check == false){
                    setLocation(parent.getAdapter().getItem(position).toString()); // 시 단위로 그리드 뷰 변경을 위한 함수 호철
                }else{
                    small_city = parent.getAdapter().getItem(position).toString();
                    Intent intent = new Intent(getApplicationContext(),Category_page.class);
                    intent.putExtra("big_city",Big_city); // 도 단위 자료 내장후 넘김
                    intent.putExtra("small_city",small_city); // 시 단위 자료 내장후 넘김
                    startActivity(intent);

                }
            }
        });



    }


    public void setLocation(String city){
        Big_city = city;
        city_Adapter city_adapter = new city_Adapter(this,location_return.small_city(city));
        city_adapter.notifyDataSetChanged(); // 데이터 변경을 암시 하고 아래 코드로 어댑터 재설정
        city_chose.setAdapter(city_adapter);
        check = true;
    }
}