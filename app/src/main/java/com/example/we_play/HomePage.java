package com.example.we_play;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HomePage extends AppCompatActivity {

    public Context context;

    TextView name1, name2;
    Button travel;
    ImageButton ticket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.herego);

        Intent mintent = getIntent();
        String id = mintent.getStringExtra("아이디");
        String name = mintent.getStringExtra("이름");

        try {
            save("id.txt",id);
            save("name.txt",name);
        }catch (Exception e){
            System.out.println("Fail to save login data");
        }

        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);
        name1.setText(name);
        name2.setText(name);
        name1.setTextSize(15);
        name2.setTextSize(15);

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

    public void save(String filename,String date) {
        if (date != null) {
            try {
                FileOutputStream fo = openFileOutput(filename, Context.MODE_PRIVATE);
                DataOutputStream dos = new DataOutputStream(fo);
                dos.writeUTF(date);
                dos.flush();
                dos.close();
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }
        }
    }

}