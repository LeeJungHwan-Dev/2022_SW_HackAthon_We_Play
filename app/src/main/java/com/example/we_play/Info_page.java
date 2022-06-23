package com.example.we_play;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Info_page extends AppCompatActivity {

    String big_city = "";
    String category = "";
    String pic_link = "";
    String title = "";
    String info = "";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView title_img;
    TextView title_tv , info_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);

        title_img = findViewById(R.id.info_img);
        title_tv = findViewById(R.id.title);
        info_tv = findViewById(R.id.info_text);

        setinfo();


        Glide.with(this).load(pic_link).into(title_img);
        title_tv.setText(title);
        getInfo();




    }





    public void setinfo(){
        Intent intent = getIntent();
        big_city = intent.getStringExtra("지역");
        category = intent.getStringExtra("카테고리");
        pic_link = intent.getStringExtra("사진");
        title = intent.getStringExtra("제목");
    }

    public void getInfo(){
       DocumentReference documentReference =  db.collection("관광정보").document(big_city).collection(category).document(title);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                info_tv.setText(task.getResult().get("소개").toString());
            }
        });
    }

}