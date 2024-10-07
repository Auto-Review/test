package com.dd2d.ori_android.core.model._dummy

import com.dd2d.ori_android.core.model.screen_code.CodeDetailModel
import com.dd2d.ori_android.core.model.screen_code.CodeListItemModel

val DummyCodeList = List(20) {
    CodeListItemModel(
        id = it.toLong(),
        title = "코드 제목 $it",
        description = "코드 설명 $it",
        level = (1..5).random(),
        reviewTime = "asd",
        code = "코드 $it"
    )
}

val DummyCode = CodeDetailModel(
    id = 1,
    title = "코드 제목",
    description = "코드 설명",
    level = 1,
    reviewTime = "asd",
    code = "코드"
)