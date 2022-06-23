package com.example.we_play;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.Toast;
import com.example.we_play.Module.RequestApiTask;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class loginActivity extends AppCompatActivity {

   /* OAuthLogin mOAuthLoginModule = OAuthLogin.getInstance();
    ImageButton btn1, btn2, btn3;*/
    Button go_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

     /*   btn1 = findViewById(R.id.n);
        btn2 = findViewById(R.id.h);
        btn3 = findViewById(R.id.g);
        go_main = findViewById(R.id.button5);

        mOAuthLoginModule.init(
                loginActivity.this
                ,getString(R.string.naver_client_id)
                ,getString(R.string.naver_client_secret)
                ,getString(R.string.app_name)
        );

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                naverLogin();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),imformation.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                finish();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleLogin();
            }
        });


        go_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Main_page.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        });


    }

    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                String accessToken = mOAuthLoginModule.getAccessToken(getApplicationContext());
                String refreshToken = mOAuthLoginModule.getRefreshToken(getApplicationContext());
                long expiresAt = mOAuthLoginModule.getExpiresAt(getApplicationContext());
                String tokenType = mOAuthLoginModule.getTokenType(getApplicationContext());
                new RequestApiTask(getApplicationContext(), mOAuthLoginModule).execute();
            } else {
                String errorCode = mOAuthLoginModule.getLastErrorCode(getApplicationContext()).getCode();
                String errorDesc = mOAuthLoginModule.getLastErrorDesc(getApplicationContext());
                Toast.makeText(getApplicationContext(), "errorCode:" + errorCode
                        + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void naverLogin() {
        mOAuthLoginModule.startOauthLoginActivity(this, mOAuthLoginHandler);
    }

    private void googleLogin() {


    }*/
}}