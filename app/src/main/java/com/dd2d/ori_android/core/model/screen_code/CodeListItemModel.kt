package com.dd2d.ori_android.core.model.screen_code

import com.dd2d.ori_android.core.data.remote.code.response.CodeListItemResponseDTO

/** 코드 화면 - 목록 : 코드 목록 아이템 모델
 * @param id 아이디
 * @param title 제목
 * @param description 설명
 * @param level 난이도
 * @param reviewTime 복습 예정 시간 (= 알람)
 * @param code 코드 */
data class CodeListItemModel(
    val id: Long,
    val title: String,
    val description: String,
    val level: Int,
    val reviewTime: String,
    val code: String,
)

fun CodeListItemResponseDTO.toModel() = CodeListItemModel(
    id = id,
    title = title,
    description = description,
    level = level,
    reviewTime = reviewTime,
    code = code,
)
fun List<CodeListItemResponseDTO>.mapModel() = map(CodeListItemResponseDTO::toModel)