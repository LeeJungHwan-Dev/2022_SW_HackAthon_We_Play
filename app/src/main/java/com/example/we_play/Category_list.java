package com.example.we_play;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class Category_list extends AppCompatActivity {

    String big_city = "";
    String category = "";
    ImageView imageView;
    TextView textView;
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> pic_link = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        getdata();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference collectionReference = db.collection("관광정보").document("전라북도").collection(category);

        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                    String img_url = documentSnapshot.get("img_url").toString();
                    String img_url2 = "http://"+ img_url.substring(8);
                    Log.i("asd",img_url2);
                    Glide.with(getApplicationContext()).load("https://tour.jb.go.kr/attachfiles/ctnt/migration/001012/1385687953530.jpg").into(imageView);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }


    public void getdata(){
        Intent intent = getIntent();
        big_city = intent.getStringExtra("지역");
        category = intent.getStringExtra("카테고리");
    }
}