package com.example.we_play

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class loginActivity : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "577def292f78fcd3dbb3821f85cde8d4")

    }
}