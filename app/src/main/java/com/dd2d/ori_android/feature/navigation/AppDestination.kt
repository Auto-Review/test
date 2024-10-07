package com.dd2d.ori_android.feature.navigation

import com.dd2d.ori_android.R
import kotlinx.serialization.Serializable

/** 앱 내 이동할 화면
 * @see SignIn
 * @see Main*/
sealed interface AppDestination {
    /** 로그인 및 회원 가입 화면*/
    @Serializable data object SignIn : AppDestination
    /** 로그인 후 화면
     * @see MainDestination*/
    @Serializable data object Main : AppDestination
}

/** 로그인 후 앱 내 이동할 화면
 * @see AppDestination.Main
 *
 * @see Code
 * @see Notification
 * @see Profile*/
sealed interface MainDestination: AppDestination {
    /** 코드 화면*/
    @Serializable data object Code : MainDestination
    /** 알림 화면*/
    @Serializable data object Notification : MainDestination
    /** 내정보 화면*/
    @Serializable data object Profile : MainDestination
}

/** 바텀 내비게이션 메뉴*/
enum class BottomNavItem(val iconRes: Int, val label: String, val destination: MainDestination) {
    Code(iconRes = R.drawable.ic_code, label =  "코드", destination = MainDestination.Code),
    Notification(iconRes = R.drawable.ic_notification, label = "알림", destination = MainDestination.Notification),
    Profile(iconRes = R.drawable.ic_person, label = "내정보", destination = MainDestination.Profile)
}

