package com.dd2d.ori_android.feature.screen_code

internal sealed interface CodeListPageState {
    data object Loading: CodeListPageState
    data class Error(val exception: Throwable): CodeListPageState
    data object Success: CodeListPageState
}

internal sealed interface CodeDetailPageState {
    data object Loading: CodeDetailPageState
    data class Error(val exception: Throwable): CodeDetailPageState
    data object Success: CodeDetailPageState
}