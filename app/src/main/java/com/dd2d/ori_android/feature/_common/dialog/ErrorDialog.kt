package com.dd2d.ori_android.feature._common.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import io.ktor.utils.io.errors.IOException

@Composable
fun ErrorDialog(
    exception: Throwable,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "확인", color = Color.Black)
            }
        },
        title = {
            Text(
                text = exception.toMessage(),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        modifier = modifier
    )
}

private fun Throwable.toMessage() = when(this) {
    is IOException -> "네트워크 오류가 발생했습니다."
    else -> "알 수 없는 오류가 발생했습니다."
}

@Preview
@Composable
private fun ErrorDialogPrev() {
    ErrorDialog(
        exception = IOException(),
        onDismiss = {},
        modifier = Modifier
    )
}