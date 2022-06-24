package com.example.we_play;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.we_play.TickerView.TicketAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
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

public class TicketActivity extends AppCompatActivity {
    public ArrayList<String> thumbnail_url;
    public ArrayList<String> placeName;
    public ArrayList<String> placeLoc;
    public ArrayList<String> ticketDate;
    public ArrayList<String> ticketPrice;
    public ArrayList<String> ticketParticipant;

    public TicketAdapter mAdapter;
    private int count = -1;

    ImageButton btn_toHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserved);

        Intent mintent = getIntent();
        String id = mintent.getStringExtra("아이디");
        String name = mintent.getStringExtra("이름");

        btn_toHome = (ImageButton) findViewById(R.id.btn_to_main_page);
        btn_toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                intent.putExtra("아이디", id);
                intent.putExtra("이름", name);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                finish();
            }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ticketRef = db.collection("회원정보").document(id).collection("티켓기록");
        ticketRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
              @Override
              public void onComplete(@NonNull Task<QuerySnapshot> task) {
                  for (DocumentSnapshot documentSnapshot : task.getResult()) {
                      placeName.add(documentSnapshot.getId());
                      String img_url = documentSnapshot.get("Thumbnail_url").toString();
                      String img_url2 = "http://" + img_url.substring(8);
                      thumbnail_url.add(img_url2);
                      placeLoc.add(documentSnapshot.get("Location").toString());
                      ticketDate.add(documentSnapshot.get("Date").toString());
                      ticketParticipant.add(documentSnapshot.get("NumTicket").toString());
                      ticketPrice.add(documentSnapshot.get("Price").toString());
                  }
              }
          });

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_reserved_list);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mAdapter = new TicketAdapter(thumbnail_url, placeName, placeLoc, ticketDate, ticketPrice, ticketParticipant, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }
}
