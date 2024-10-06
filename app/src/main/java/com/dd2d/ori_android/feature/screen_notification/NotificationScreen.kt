package com.dd2d.ori_android.feature.screen_notification

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = "알림 화면")
    }
}

@Preview
@Composable
private fun NotificationScreenPrev() {
    NotificationScreen(
        modifier = Modifier
            .fillMaxSize()
    )
}