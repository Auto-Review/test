package com.dd2d.ori_android.feature.screen_code

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CodeScreen(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = "코드 화면")
    }
}

@Preview
@Composable
private fun CodeScreenPrev() {
    CodeScreen(
        modifier = Modifier
            .fillMaxSize()
    )
}