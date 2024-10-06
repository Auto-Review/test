package com.dd2d.ori_android.feature.screen_profile

import android.provider.ContactsContract.Profile
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dd2d.ori_android.feature.screen_sign_in.SignInScreen

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = "내정보 화면")
    }
}

@Preview
@Composable
private fun ProfileScreenPrev() {
    ProfileScreen(
        modifier = Modifier
            .fillMaxSize()
    )
}