package com.example.we_play;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class infoActivity extends AppCompatActivity {
    Button infoEdit, journal, payment, set, servic, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        infoEdit = findViewById(R.id.infoEdit);
        journal = findViewById(R.id.journal);
        payment = findViewById(R.id.payment);
        set = findViewById(R.id.set);
        servic = findViewById(R.id.service);
        delete = findViewById(R.id.delete);

        infoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), info_Edit_Activity.class);
                startActivity(intent);
            }
        });

        journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), info_journal_Activity.class);
                startActivity(intent);
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), info_payment_Activity.class);
                startActivity(intent);
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), info_Set_Activity.class);
                startActivity(intent);
            }
        });

        servic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), info_Service_Activity.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), info_delete_Activity.class);
                startActivity(intent);
            }
        });
    }
}