package com.dd2d.ori_android.core.data.source.remote._common

import kotlinx.serialization.Serializable

@Serializable
data class DataResponseDTO<T>(
    val status: String,
    val data: T,
    val message: String
)