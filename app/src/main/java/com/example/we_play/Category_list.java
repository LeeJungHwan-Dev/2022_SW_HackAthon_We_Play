package com.example.we_play;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.we_play.GridView.cl_list_Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Category_list extends AppCompatActivity {

    String big_city = "";
    String category = "";
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> pic_link = new ArrayList<>();
    ArrayList<String> location = new ArrayList<>();

    TextView location_title;
    GridView gridView;
    ImageButton return_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        gridView = findViewById(R.id.cu_gird_view);
        location_title = findViewById(R.id.location_title);
        return_category = findViewById(R.id.button);

        getdata();

        location_title.setText(big_city);


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference collectionReference = db.collection("관광정보").document(big_city).collection(category);
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot documentSnapshot : task.getResult()){

                    try {
                        String img_url = documentSnapshot.get("thumbnail_url").toString();
                        String img_url2 = "http://"+ img_url.substring(8);
                        pic_link.add(img_url2);
                    }catch (Exception e){
                        String img_url = documentSnapshot.get("img_url").toString();
                        String img_url2 = "http://"+ img_url.substring(8);
                        pic_link.add(img_url2);
                    }



                    title.add(documentSnapshot.getId());

                    try {
                        location.add(documentSnapshot.get("주소").toString());
                    }catch (Exception e){
                        try {
                            location.add(documentSnapshot.get("위치정보").toString());
                        }catch (Exception a){
                            location.add(documentSnapshot.get("장소").toString());
                        }

                    }

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                cl_list_Adapter adapter = new cl_list_Adapter(getApplicationContext(),title,pic_link,location);
                gridView.setAdapter(adapter);
            }
        });



        return_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Category_page.class);
                intent.putExtra("big_city",big_city);
                startActivity(intent);
            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   go_info(parent.getAdapter().getItem(position).toString(),position);
            }
        });


    }


    public void getdata(){
        Intent intent = getIntent();
        big_city = intent.getStringExtra("지역");
        category = intent.getStringExtra("카테고리");
    }

    public void go_info(String str,int pos){
        Intent intent = new Intent(getApplicationContext(),Info_page.class);
        intent.putExtra("지역",big_city);
        intent.putExtra("카테고리",category);
        intent.putExtra("사진",pic_link.get(pos));
        intent.putExtra("제목",str);
        startActivity(intent);
    }


}