package com.example.we_play;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });





        /**
         *
         * 아래코드는 간단하게 firebase에서 등록된 텍스트 정보를 가져오는 예시입니다.
         *
         * */


        CollectionReference collectionReference = db.collection("doc"); // <- 맨 첫번째줄이 collection - > document -> filed 순 입니다.

        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot documentSnapshot : task.getResult()){ // <- doc 컬렉션에서 가져온 정보를 for문을 사용하여 documentSnapshot에 저장하고
                    Toast.makeText(getApplicationContext(), documentSnapshot.getData().get("name").toString(),Toast.LENGTH_SHORT).show();
                    // 이후 꺼낸 정보에서 "name" 이라는 필드값이 존재하면 값을 꺼내 toast 메세지로 출력합니다.
                    // db를 보면서 진행해보시면 이해가 빠를겁니다.
                }
            }
        });


    }
}