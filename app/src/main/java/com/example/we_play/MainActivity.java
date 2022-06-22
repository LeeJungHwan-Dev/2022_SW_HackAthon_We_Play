package com.example.we_play;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import android.content.pm.Signature;
import java.security.MessageDigest;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAppKeyHash();

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

    // 카카오 로그인 시 해시 키 메소드
   private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
            }
        } catch (Exception e) {
            Log.e("name not found", e.toString());
        }
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