package com.dd2d.ori_android.core.data.remote.code.request

import kotlinx.serialization.Serializable

@Serializable
data class CodeSearchRequestDTO(
    val page: Int,
    val take: Int,
    val sort: String,
    val keyword: String?,
)
