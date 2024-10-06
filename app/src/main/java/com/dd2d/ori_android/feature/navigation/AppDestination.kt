package com.dd2d.ori_android.feature.navigation

import kotlinx.serialization.Serializable

/** 앱 내 이동할 화면
 * @see SignIn
 * @see Code
 * @see Notification
 * @see Profile*/
sealed interface AppDestination {
    /** 로그인 및 회원 가입 화면*/
    @Serializable
    data object SignIn : AppDestination
    /** 코드 화면*/
    @Serializable
    data object Code : AppDestination
    /** 알림 화면*/
    @Serializable
    data object Notification : AppDestination
    /** 내정보 화면*/
    @Serializable
    data object Profile : AppDestination
}