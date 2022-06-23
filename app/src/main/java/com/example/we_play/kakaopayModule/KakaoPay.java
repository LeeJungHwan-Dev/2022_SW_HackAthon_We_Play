package com.example.we_play.kakaopayModule;


import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.iamport.sdk.data.sdk.IamPortRequest;
import com.iamport.sdk.data.sdk.PG;
import com.iamport.sdk.data.sdk.PayMethod;
import com.iamport.sdk.domain.core.Iamport;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import kotlin.Unit;

public class KakaoPay extends Application {

    String name = ""; // 상품 이름
    String amount = ""; // 상품 가격
    String email = "";
    String buyer_name = ""; // 구매자 이름
    String date = "";
    String location = "";
    String img_url = "";
    String peoplecount = "";
    Application application; // 아임포트 인스턴스를 craete 하기 위한 Application 변수
    FirebaseFirestore db = FirebaseFirestore.getInstance();



    public KakaoPay(String name, String amount ,String date ,String peoplecount,String img_url, String location ,String email,Application application){
        this.name = name;
        this.amount = amount;
        this.application = application;
        this.date = date;
        this.peoplecount = peoplecount;
        this.img_url = img_url;
        this.location = location;
        this.email = email;
    }

    /**
     * 아임포트 라이브러리를 사용하여 KG 이니시스를 연동했습니다.
     * 아임포트 홈페이지에서 기본 PG사를 변경할 경우 카카오페이가 아닌 다른 통합 PG사를 이용할 수 있습니다.
     * 하지만 당장 결제 화면을 보여주기에는 카카오페이 테스트 화면이 가장 적절한거 같아 기본 값을 카카오페이로 설정해놨습니다.
     *
     * */



    public void pay() {
        Iamport.INSTANCE.create(application);

        buyer_name = "구매자";

        IamPortRequest request = IamPortRequest.builder()
                .pg(PG.kcp.makePgRawName(""))
                .pay_method(PayMethod.card.name())
                .name(this.name)
                .merchant_uid("mid_" + (new Date()).getTime())
                .amount(this.amount)
                .buyer_name(this.buyer_name).build();

        Iamport.INSTANCE.payment("imp53321413", null, null, request,
                iamPortApprove -> {
                    // (Optional) CHAI 최종 결제전 콜백 함수.
                    return Unit.INSTANCE;
                }, iamPortResponse -> {
                    // 최종 결제결과 콜백 함수.
                    String responseText = iamPortResponse.toString();
                    Log.d("IAMPORT_SAMPLE", responseText); // 완료될 경우 여기서 이벤트를 처리하면 됨. 차후 전자 티켓 추가 이벤트를 처리하면 될 거 같음.



                    if(responseText.contains("imp_success=true")){

                        Map<String,String> pay_info = new HashMap<>();
                        pay_info.put("Overdue","false");
                        pay_info.put("Used","false");
                        pay_info.put("date",date);
                        pay_info.put("numTicket",peoplecount);
                        pay_info.put("price",amount);
                        pay_info.put("location",location);
                        pay_info.put("img_url",img_url);


                        db.collection("회원정보").document(email).collection("티켓기록").document(name).set(pay_info).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
                    }



                    return Unit.INSTANCE;
                });
    }




}


