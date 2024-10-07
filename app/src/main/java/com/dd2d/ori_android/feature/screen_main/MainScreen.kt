package com.dd2d.ori_android.feature.screen_main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.dd2d.ori_android.feature.navigation.BottomNavItem
import com.dd2d.ori_android.feature.navigation.MainDestination
import com.dd2d.ori_android.feature.screen_code.CodeScreen
import com.dd2d.ori_android.feature.screen_notification.NotificationScreen
import com.dd2d.ori_android.feature.screen_profile.ProfileScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    var currentDestination: MainDestination by remember { mutableStateOf(MainDestination.Code) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            BottomNavItem.entries.forEach { item ->
                item(
                    selected = currentDestination == item.destination,
                    onClick = { currentDestination = item.destination },
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = item.iconRes),
                            contentDescription = item.label
                        )
                    },
                    label = { Text(text = item.label) },
                )
            }
        },
        containerColor = Color.White,
        contentColor = Color.White,
        modifier = modifier
    ) {
        Scaffold(
            containerColor = Color.White,
            modifier = Modifier
                .statusBarsPadding()
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .consumeWindowInsets(innerPadding)
                    .fillMaxSize()
            ) {
                when(currentDestination) {
                    MainDestination.Code -> CodeScreen(modifier = Modifier.matchParentSize())
                    MainDestination.Notification -> NotificationScreen(modifier = Modifier.matchParentSize())
                    MainDestination.Profile -> ProfileScreen(modifier = Modifier.matchParentSize())
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPrev() {
    MainScreen(
        modifier = Modifier
            .fillMaxSize()
    )
}