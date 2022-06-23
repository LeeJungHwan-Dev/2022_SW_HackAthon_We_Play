package com.example.we_play.kakaopayModule;


import android.app.Application;
import android.util.Log;

import com.iamport.sdk.data.sdk.IamPortRequest;
import com.iamport.sdk.data.sdk.PG;
import com.iamport.sdk.data.sdk.PayMethod;
import com.iamport.sdk.domain.core.Iamport;

import java.util.Date;

import kotlin.Unit;

public class KakaoPay extends Application {

    String name = ""; // 상품 이름
    String amount = ""; // 상품 가격
    String buyer_name = ""; // 구매자 이름
    Application application; // 아임포트 인스턴스를 craete 하기 위한 Application 변수

    public KakaoPay(String name, String amount , String buyer_name , Application application){
        this.name = name;
        this.amount = amount;
        this.buyer_name = buyer_name;
        this.application = application;
    }

    /**
     * 아임포트 라이브러리를 사용하여 KG 이니시스를 연동했습니다.
     * 아임포트 홈페이지에서 기본 PG사를 변경할 경우 카카오페이가 아닌 다른 통합 PG사를 이용할 수 있습니다.
     * 하지만 당장 결제 화면을 보여주기에는 카카오페이 테스트 화면이 가장 적절한거 같아 기본 값을 카카오페이로 설정해놨습니다.
     *
     * */



    public void pay() {
        Iamport.INSTANCE.create(application);


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
                    return Unit.INSTANCE;
                });
    }
}

