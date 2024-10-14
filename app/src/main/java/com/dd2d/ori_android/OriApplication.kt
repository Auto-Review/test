package com.dd2d.ori_android

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OriApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(
            context = this@OriApplication,
            appKey = BuildConfig.KAKAO_SIGN_IN_SDK_KEY
        )
    }
}