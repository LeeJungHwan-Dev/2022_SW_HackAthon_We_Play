package com.example.we_play.Module;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.we_play.Main_page;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nhn.android.naverlogin.OAuthLogin;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class RequestApiTask extends AsyncTask<Void, Void, String> {

    String cp_name , cp_number, cp_email;

    private final Context mContext;
    private final OAuthLogin mOAuthLoginModule;
    public RequestApiTask(Context mContext, OAuthLogin mOAuthLoginModule) {
        this.mContext = mContext;
        this.mOAuthLoginModule = mOAuthLoginModule;
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected String doInBackground(Void... params) {
        String url = "https://openapi.naver.com/v1/nid/me";
        String at = mOAuthLoginModule.getAccessToken(mContext);
        return mOAuthLoginModule.requestApi(mContext, at, url);
    }

    protected void onPostExecute(String content) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        String email = "";

        try {
            JSONObject loginResult = new JSONObject(content);
            if (loginResult.getString("resultcode").equals("00")){
                JSONObject response = loginResult.getJSONObject("response");

                String name = response.getString("name");
                email = response.getString("email");
                String mobile = response.getString("mobile");

                user.put("Site", "naver");
                user.put("Name", name);
                user.put("PhoneNumber", mobile);
                user.put("BirthDay", "");
                user.put("ID", "");
                user.put("Password", "");

                Toast.makeText(mContext, "name : "+ name+" email : "+email +" mobile : "+mobile, Toast.LENGTH_SHORT).show();
                cp_email = email;
                cp_name = name;
                cp_number = mobile;
            }

            db.collection("회원정보").document(email).set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                            Intent intent = new Intent(mContext, Main_page.class);
                            intent.putExtra("이메일", cp_email);
                            intent.putExtra("이름",cp_name);
                            intent.putExtra("번호",cp_number);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}