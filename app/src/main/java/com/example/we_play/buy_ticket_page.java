package com.example.we_play;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.we_play.kakaopayModule.KakaoPay;
import com.iamport.sdk.domain.core.Iamport;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class buy_ticket_page extends AppCompatActivity {

    String title , pic_link , location , name , date , url , email;
    ImageView buy_img;
    TextView buy_title,buy_location,chose_day,count_peple,total;
    CalendarView set_day;
    ImageButton up, down, go_pay;


    int people_count = 0;
    int totalpay = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket_page);


        Iamport.INSTANCE.init(this);


        buy_img = findViewById(R.id.buy_img);
        buy_title = findViewById(R.id.buy_title);
        buy_location = findViewById(R.id.buy_location);
        chose_day = findViewById(R.id.chose_day);
        count_peple = findViewById(R.id.cout_peple);
        total = findViewById(R.id.total);
        set_day = findViewById(R.id.set_day);
        up = findViewById(R.id.peple_up);
        down = findViewById(R.id.peple_down);
        go_pay = findViewById(R.id.go_pay);

       Map<String,String> map =  CheckFileExist();

       email = map.get("아이디");
       name = map.get("이름");


        setData();

        url = pic_link;

        Glide.with(this).load(pic_link).into(buy_img);
        buy_title.setText(title);
        buy_location.setText(location);

        set_day.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                chose_day.setText("날짜 : "+String.valueOf(year)+"/"+String.valueOf(month)+"/"+String.valueOf(dayOfMonth));
                date = String.valueOf(year)+"."+String.valueOf(month)+"."+String.valueOf(dayOfMonth);
            }
        });


        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people_count++;
                totalpay += 10000;
                count_peple.setText("인원 : " + String.valueOf(people_count) +" 명");
                total.setText("총 금액 : " + String.valueOf(totalpay)+" 원");
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people_count--;
                totalpay -= 10000;
                count_peple.setText("인원 : " + String.valueOf(people_count) +" 명");
                total.setText("총 금액 : " + String.valueOf(totalpay)+" 원");
                if(totalpay < 0 || people_count < 0){
                    people_count = 0;
                    totalpay = 0;
                    total.setText("총 금액 : 0 원");
                    count_peple.setText("인원 : 0 명");
                }
            }
        });

        go_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalpay <= 0){
                    Toast.makeText(getApplicationContext(),"표를 한장 이상 구매해주세요.",Toast.LENGTH_SHORT).show();
                }else {
                    //String name, String amount ,String date ,String peoplecount,Application application
                    KakaoPay kakaopay = new KakaoPay(title, String.valueOf(totalpay), date,"구매자",url,location,email, getApplication());
                    kakaopay.pay();
                }
            }
        });





    }

    public void setData(){
        Intent intent = getIntent();
        title = intent.getStringExtra("제목");
        pic_link = intent.getStringExtra("사진");
        location = intent.getStringExtra("장소");
    }


    public Map<String, String> CheckFileExist() {
        Map<String, String> data = new HashMap<>();
        try {
            FileInputStream fi = openFileInput("id.txt");
            DataInputStream dis = new DataInputStream(fi);
            data.put("아이디", dis.readUTF());
            dis.close();
            fi.close();
            fi = openFileInput("name.txt");
            dis = new DataInputStream(fi);
            data.put("이름", dis.readUTF());
            dis.close();
            fi.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            data.put("아이디", null);
        } catch (IOException e) {
            e.printStackTrace();
            data.put("아이디", null);
        }
        return data;
    }

}