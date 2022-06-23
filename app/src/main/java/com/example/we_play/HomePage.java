package com.example.we_play;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    public Context context;

    TextView name1, name2;
    Button travel;
    ImageButton ticket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.herego);

        Intent intent = getIntent();
        String id = intent.getStringExtra("아이디");
        String name = intent.getStringExtra("이름");

        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);
        name1.setText(name);
        name2.setText(name);

        travel = (Button) findViewById(R.id.button1);
        ticket = (ImageButton) findViewById(R.id.Imbutton2);

        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TravelActivity.class);
                intent.putExtra("아이디", id);
                intent.putExtra("이름", name);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        });

        ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TicketActivity.class);
                intent.putExtra("아이디", id);
                intent.putExtra("이름", name);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        });

    }
}