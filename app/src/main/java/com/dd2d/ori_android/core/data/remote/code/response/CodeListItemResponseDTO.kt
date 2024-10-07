package com.dd2d.ori_android.core.data.remote.code.response

import kotlinx.serialization.Serializable

@Serializable
data class CodeListItemResponseDTO(
    val id: Long,
    val title: String,
    val description: String,
    val level: Int,
    val reviewTime: String,
    val code: String,
)
