package com.dd2d.ori_android.feature._common.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.ori_android.feature._common.theme.MainColor

@Composable
fun EmptyDetailPage(
    modifier: Modifier = Modifier,
    text: String = "항목을 선택해주세요."
) {
    Scaffold(
        containerColor = Color.White,
        modifier = modifier
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MainColor,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}

@Preview
@Composable
private fun EmptyDetailPagePage() {
    EmptyDetailPage(
        modifier = Modifier
            .fillMaxSize()
    )
}