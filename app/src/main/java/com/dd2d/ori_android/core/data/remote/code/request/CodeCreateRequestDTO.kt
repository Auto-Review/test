package com.dd2d.ori_android.core.data.remote.code.request

import kotlinx.serialization.Serializable

@Serializable
data class CodeCreateRequestDTO(
    val title: String,
    val description: String,
    val level: Int,
    val reviewTime: String,
    val code: String,
)
