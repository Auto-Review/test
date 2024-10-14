package com.dd2d.ori_android.feature.screen_code

import com.dd2d.ori_android.core.model.screen_code.CodeDetailModel

internal sealed interface CodeListPageState {
    data object Loading: CodeListPageState
    data class Error(val exception: Throwable): CodeListPageState
    data class Success(val isLastPage: Boolean) : CodeListPageState
}

internal sealed interface CodeDetailPageState {
    data object Loading: CodeDetailPageState
    data class Error(val exception: Throwable): CodeDetailPageState
    data class Success(val data: CodeDetailModel): CodeDetailPageState
}