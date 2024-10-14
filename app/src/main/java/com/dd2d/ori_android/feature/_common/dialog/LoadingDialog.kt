package com.dd2d.ori_android.feature._common.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.dd2d.ori_android.feature._common.theme.MainColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingDialog(
    modifier: Modifier = Modifier
) {
    BasicAlertDialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        ),
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(alpha = 0.3F))
        ) {
            CircularProgressIndicator(
                color = MainColor,
                trackColor = MainColor.copy(alpha = 0.5F),
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingDialogPrev() {
    Column {

        LoadingDialog(
            modifier = Modifier
        )

    }
}