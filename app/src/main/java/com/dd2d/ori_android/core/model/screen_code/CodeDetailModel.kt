package com.dd2d.ori_android.core.model.screen_code

import com.dd2d.ori_android.core.data.remote.code.response.CodeDetailResponseDTO

/** 코드 화면 - 상세 : 코드 상세 모델*/
data class CodeDetailModel(
    val id: Long,
    val title: String,
    val description: String,
    val level: Int,
    val reviewTime: String,
    val code: String,
    val createdAt: String,
    val updatedAt: String,
)

fun CodeDetailResponseDTO.toModel() = CodeDetailModel(
    id = id,
    title = title,
    description = description,
    level = level,
    reviewTime = reviewTime,
    code = code,
    createdAt = createdAt,
    updatedAt = updatedAt,
)