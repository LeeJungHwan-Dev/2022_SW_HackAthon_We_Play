package com.example.we_play;

import static android.content.ContentValues.TAG;
import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class information extends AppCompatActivity {

    EditText Name, Number, Year, Month, Day, Id, Pass, PassCheck;
    Button button,button3;
    ImageView setImage;
    TextView tv_error_email;
    Boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imformation);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Name=(EditText)findViewById(R.id.EditName);
        Number=(EditText)findViewById(R.id.EditNumber);
        Year=(EditText)findViewById(R.id.EditYear);
        Month=(EditText)findViewById(R.id.EditMonth);
        Day=(EditText)findViewById(R.id.EditDay);
        Id=(EditText)findViewById(R.id.EditId); // id
        Pass=(EditText)findViewById(R.id.EditPass);
        PassCheck=(EditText)findViewById(R.id.EditPassCheck);
        button=findViewById(R.id.button1);
        setImage = (ImageView)findViewById(R.id.setImage);
        button3=findViewById(R.id.button3);
        tv_error_email=findViewById(R.id.tv_error_email);

        Id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()){
                    tv_error_email.setText("이메일 형식으로 입력해주세요.");    // 경고 메세지
                    //Id.setBackgroundResource(R.drawable.red_edittext);  // 적색 테두리 적용
                } else{
                    tv_error_email.setText("");         //에러 메세지 제거
                    //Id.setBackgroundResource(R.drawable.white_edittext);  //테투리 흰색으로 변경
                }
            }// afterTextChanged()..
        });


        button3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                two_id_check(Id.getText().toString());
            }
        }
        );

        /* if(value!=null){

        //Id.setBackgroundResource(R.drawable.red_edittext);  // 적색 테두리 적용
        }
        else{
        button3.setText("확인 완료");
        }
        }*/


        PassCheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(Pass.getText().toString().equals(PassCheck.getText().toString())){
                    setImage.setImageResource(R.drawable.check);
                } else {
                    setImage.setImageResource(R.drawable.xxx);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getPass = Pass.getText().toString();
                String getName = Name.getText().toString();
                int getNumber = parseInt(Number.getText().toString());
                String Birthday = Year.getText().toString() + "/" + Month.getText().toString() + "/" + Day.getText().toString();
                String getId = Id.getText().toString();

                two_id_check(getId);

                if(check == false) {
                Map<String, Object> user = new HashMap<>();

                user.put("Name", getName);
                user.put("PhoneNumber", getNumber);
                user.put("BirthDay", Birthday);
                user.put("ID", getId);
                user.put("Password", getPass);
                user.put("Site", "home");

                // Add a new document with a ID
                db.collection("회원정보")
                .document(getId)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
                } else {
                    Toast.makeText(getApplicationContext(),"중복된 아이디가 존재합니다",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void two_id_check(String id){
        check = false;

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = db.collection("회원정보");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot documentSnapshot : task.getResult()){
                    if(id.equals(documentSnapshot.getId())){
                        check = true; // 중복값이 있으면 true
                    }
                }
            }
        }).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(check == true){
                    Toast.makeText(getApplicationContext(),"중복값이 존재합니다",Toast.LENGTH_SHORT).show();
                }
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






