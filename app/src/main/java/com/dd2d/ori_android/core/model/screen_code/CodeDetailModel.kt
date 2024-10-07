package com.dd2d.ori_android.core.model.screen_code

import java.time.LocalDateTime

/** 코드 화면 - 상세 : 코드 상세 모델*/
data class CodeDetailModel(
    val id: Long,
    val title: String,
    val description: String,
    val level: Int,
    val reviewTime: String,
    val code: String,
)
