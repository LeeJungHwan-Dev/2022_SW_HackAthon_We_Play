package com.example.we_play;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> data = CheckFileExist();
        if(data.get("아이디") == null){
            Intent intent = new Intent(getApplicationContext(), startActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), TravelActivity.class);
            intent.putExtra("아이디",data.get("아이디"));
            intent.putExtra("이름",data.get("이름"));
            startActivity(intent);
            overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        }

//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(getApplicationContext(), startActivity.class);
//                startActivity(intent);
//            }
//        };
//
//        Timer timer = new Timer();
//        timer.schedule(timerTask, 1500);


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
