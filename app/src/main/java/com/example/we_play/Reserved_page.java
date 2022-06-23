package com.example.we_play;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.we_play.GridView.RecyclerViewAdapter;
import com.example.we_play.Recyclerview.reserved_Adapter;
import com.example.we_play.Recyclerview.reserved_data;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Dictionary;

public class Reserved_page extends AppCompatActivity {
    private ArrayList<Dictionary> mArrayList;
    private reserved_Adapter mAdapter;
    private int count = -1;

    ImageButton btn_back;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserved);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_reserved_list);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        // get ticket information from Firebase
        mArrayList = new ArrayList<>();
        CollectionReference ticketCollection = db.collection("회원정보").document(id).collection("티켓 기록");
//        ticketCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                for(DocumentSnapshot documentSnapshot : task.getResult()){
//
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.i("aa","test1");
//            }
//        }).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(img_link,title,location,getApplicationContext(),rating);
//                foodview.setAdapter(mAdapter);
//                foodview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                mAdapter.notifyDataSetChanged();
//            }
//        });

        // set Adapter
        mAdapter = new reserved_Adapter(mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        btn_back = findViewById(R.id.btn_to_main_page);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
