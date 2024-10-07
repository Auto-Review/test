package com.dd2d.ori_android.core.model.screen_code

import com.dd2d.ori_android.core.data.remote.code.request.CodeSearchRequestDTO

/** 코드 화면 - 목록 : 목록 검색 모델*/
data class CodeSearchModel(
    val page: Int,
    val take: Int,
    val sort: String = "CREATED_AT:DESC",
    val keyword: String? = null,
)

fun CodeSearchModel.toDTO() = CodeSearchRequestDTO(
    page = page,
    take = take,
    sort = sort,
    keyword = keyword,
)