package com.example.we_play;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         *
         * 메인 시작 페이지로 첫 로딩 화면 입니다.
         * 1초간의 대기를 하고 다음 페이지로 넘어갑니다.
         * 차후 이 페이지에 계정 로그인 / 회원가입을 구현해야 합니다.
         * 차후 Glide 라이브러리를 사용해 이미지도 추가해야함.
         * 차후 overridePendingTransition(); 창 전환 애니메이션도 구현할 예정
         * */

        Go_main_page(context);





    }


    public void Go_main_page(Context context){

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(context,Main_page.class);
                startActivity(intent);
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask,1500);

    }
}