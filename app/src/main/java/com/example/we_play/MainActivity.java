package com.example.we_play;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iamport.sdk.data.sdk.IamPortRequest;
import com.iamport.sdk.data.sdk.PG;
import com.iamport.sdk.data.sdk.PayMethod;
import com.iamport.sdk.domain.core.Iamport;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import kotlin.Unit;

public class MainActivity extends AppCompatActivity {


    EditText editTextName;
    EditText editTextPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //editTextName = findViewById(R.id.editName);
        //editTextPrice = findViewById(R.id.editPrice);

      /*TimerTask timerTask = new TimerTask() {
           @Override
           public void run() {
               Intent intent = new Intent(getApplicationContext(),Main_page.class);
               startActivity(intent);
           }
       };
       Timer timer = new Timer();
       timer.schedule(timerTask,1500);

*/

        Iamport.INSTANCE.create(getApplication());
        Iamport.INSTANCE.init(this);

        // 버튼 클릭 이벤트
        Button button = findViewById(R.id.buttonPay);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IamPortRequest request = IamPortRequest.builder()
                        .pg(PG.kcp.makePgRawName(""))
                        .pay_method(PayMethod.card.name())
                        .name("JAVA칩 프라푸치노 주문이요")
                        .merchant_uid("mid_" + (new Date()).getTime())
                        .amount("3200")
                        .buyer_name("김아임포트").build();

                Iamport.INSTANCE.payment("imp53321413", null, null, request,
                        iamPortApprove -> {
                            // (Optional) CHAI 최종 결제전 콜백 함수.
                            return Unit.INSTANCE;
                        }, iamPortResponse -> {
                            // 최종 결제결과 콜백 함수.
                            String responseText = iamPortResponse.toString();
                            Log.d("IAMPORT_SAMPLE", responseText);
                            Toast.makeText(getApplicationContext(), responseText, Toast.LENGTH_LONG).show();
                            return Unit.INSTANCE;
                        });
            }


        });




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Iamport.INSTANCE.close();
    }
}
