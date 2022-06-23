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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class imformation extends AppCompatActivity {

  EditText Name, Number, Year, Month, Day, Id, Pass, PassCheck;
  Button button,button3;
  ImageView setImage;
  TextView tv_error_email;

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
    Id=(EditText)findViewById(R.id.EditId);
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
            Id.setBackgroundResource(R.drawable.red_edittext);  // 적색 테두리 적용
          }
          else{
            tv_error_email.setText("");         //에러 메세지 제거
            Id.setBackgroundResource(R.drawable.white_edittext);  //테투리 흰색으로 변경
          }
        }// afterTextChanged()..
      });


    button3.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View view) {

        CollectionReference collectionReference = db.collection("회원정보");




      }});




   /* if(value!=null){

      //Id.setBackgroundResource(R.drawable.red_edittext);  // 적색 테두리 적용
    }
    else{
      button3.setText("확인 완료");
    }
  }*/


    PassCheck.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(Pass.getText().toString().equals(PassCheck.getText().toString())){
          setImage.setImageResource(R.drawable.check);
        } else {
          setImage.setImageResource(R.drawable.xxx);
        }
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });


   button.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View view) {

        String getPass = Pass.getText().toString();
          String getName = Name.getText().toString();
          int getNumber = parseInt(Number.getText().toString());
          String Birthday = Year.getText().toString() + "/" + Month.getText().toString() + "/" + Day.getText().toString();
          String getId = Id.getText().toString();



        Map<String, Object> user = new HashMap<>();

        user.put("Name", getName);
        user.put("PhoneNumber", getNumber);
        user.put("BirthDay", Birthday);
        user.put("ID", getId);
        user.put("Password", getPass);
        user.put("Site","home");

        

// Add a new document with a generated ID
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

      }});
  }}



