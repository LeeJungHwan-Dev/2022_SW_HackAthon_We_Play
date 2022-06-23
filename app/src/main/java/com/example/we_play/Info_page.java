package com.example.we_play;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    String location = "";
    String title = "";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageButton back,buy;
    ImageView title_img;
    TextView title_tv , info_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);

        title_img = findViewById(R.id.info_img);
        title_tv = findViewById(R.id.title);
        info_tv = findViewById(R.id.info_text);
        back = findViewById(R.id.back_category_list);
        buy = findViewById(R.id.buy);

        setinfo();
        title_img.setClipToOutline(true);


        Glide.with(this).load(pic_link).into(title_img);
        title_tv.setText(title);
        getInfo();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),buy_ticket_page.class);
                intent.putExtra("제목",title);
                intent.putExtra("사진",pic_link);
                intent.putExtra("장소",location);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                finish();
            }
        });



    }

    public void setinfo(){
        Intent intent = getIntent();
        big_city = intent.getStringExtra("지역");
        category = intent.getStringExtra("카테고리");
        pic_link = intent.getStringExtra("사진");
        title = intent.getStringExtra("제목");
        location = intent.getStringExtra("위치");
    }

    public void getInfo(){
       DocumentReference documentReference =  db.collection("관광정보").document(big_city).collection(category).document(title);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                try {
                    info_tv.setText(task.getResult().get("description").toString());
                }catch (Exception e){
                    info_tv.setText("보다 좋은 서비스를 위해 정보를 준비중 입니다.");
                }
            }
        });
    }

}