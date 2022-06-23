package com.example.we_play;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginActivity extends AppCompatActivity {

    EditText email, pw;
    ImageButton btn_main;
    TextView tv_error_email_login;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.et_email);
        pw = (EditText) findViewById(R.id.et_pw);
        btn_main = (ImageButton) findViewById(R.id.btn_start_main);
        tv_error_email_login = (TextView) findViewById(R.id.tv_error_email_login);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()){
                    tv_error_email_login.setText("이메일 형식으로 입력해주세요.");    // 경고 메세지
                    tv_error_email_login.setTextColor(Color.RED);
                }
                else{
                    tv_error_email_login.setText("");         //에러 메세지 제거
                }
            }
        });

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hashedId = hashing(email.getText().toString());
                String hashedPW = hashing(pw.getText().toString());

                CollectionReference collectionReference = db.collection("회원정보");
                collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot documentSnapshot : task.getResult()){
                            if(hashedId.equals(documentSnapshot.getId()) && hashedPW.equals(documentSnapshot.get("Password"))){
                                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                                intent.putExtra("아아디", hashedId);
                                intent.putExtra("이름", documentSnapshot.get("Name").toString());
                                startActivity(intent);
                                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                                finish();
                                break;
                            }
                        }
                    }
                });
            }
        });
    }

    public String hashing(String str) {
        String result;
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(str.getBytes());
            byte byteData[] = sh.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString(byteData[i] & 0xff + 0x100, 16).substring(1));
            }
            result = sb.toString();
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }
}
