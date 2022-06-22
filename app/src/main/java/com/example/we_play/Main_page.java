package com.example.we_play;

import androidx.appcompat.app.AppCompatActivity;
import com.example.we_play.Module.location_return;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Main_page extends AppCompatActivity {

    Spinner location1,location2; // 도 , 시 단위를 선택하는 스피너 1,2
    Button go_category; // 다음 페이지로 넘어가기 위한 버튼
    location_return location_return = new location_return(); // 지역명을 불러오기 위한 클래스 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        location1 = findViewById(R.id.set_location1);
        location2 = findViewById(R.id.set_location2);
        go_category = findViewById(R.id.go_category_page);

        firstLocation_set(); // 첫 지역 디폴트 지역 설정 및 추가

        /**
         * lcation1은 '도' 단위를 선택하는 스피너입니다.
         * 해당 스피너에서 아이템을 선택하면 setLocation이라는 함수가 작동하고 location1에서 선택한 '도'를 넘겨 그 다음 단위인 '시'를 리턴 받습니다.
         * */


        location1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setLocation(location1.getSelectedItem().toString()); // 아이템이 선택 되었을 때
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                setLocation(location1.getSelectedItem().toString()); // 아이템이 선택되지 않았을 때
            }
        });


        go_category.setOnClickListener(new View.OnClickListener() { // 완료 버튼을 눌렀을 때 이벤트 처리
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Category_page.class); //다음 페이지로 넘어가기위한 인텐트를 생성하고
                intent.putExtra("도",location1.getSelectedItem().toString()); // putExtra를 사용하여 (key,value) 방식으로 값 전달
                intent.putExtra("시",location2.getSelectedItem().toString());
                startActivity(intent); // 인텐트 시작

                // 차후 overridePendingTransition(); 창 전환 애니메이션도 구현할 예정

            }
        });



    }


    public void setLocation(String city){
        //스피너는 어뎁터 + ui.xml 구조여서 어뎁터를 설정하고 스피너에 장착해야함.
        //큰 단위 '도' 를 리턴 받고 '시'를 스피너에 추가한다.
        ArrayAdapter<String> location2_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, location_return.small_city(city));
        location2_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location2.setAdapter(location2_adapter);
    }

    public  void firstLocation_set(){
        ArrayAdapter<String> location1_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,location_return.Big_city());
        location1_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location1.setAdapter(location1_adapter);
        location1.setSelection(0);
    }
}