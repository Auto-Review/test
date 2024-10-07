package com.dd2d.ori_android.feature.screen_code.page

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CodeDetailPage(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BackHandler(onBack = onBack)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = "Code Detail Page")
    }
}

@Preview
@Composable
private fun CodeDetailPagePrev() {
    CodeDetailPage(
        onBack = {},
        modifier = Modifier
            .fillMaxSize()
    )
}