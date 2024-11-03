package com.dd2d.ori_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.dd2d.ori_android.feature._common.theme.OriandroidTheme
import com.dd2d.ori_android.feature.navigation.AppDestination
import com.dd2d.ori_android.feature.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val inInitializing = MutableStateFlow(true)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            initApp()
            setKeepOnScreenCondition { inInitializing.value }
        }
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            OriandroidTheme {
                App(
                    startDestination = AppDestination.Main,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }

    private fun initApp() {
        lifecycleScope.launch {
            // 초기화 작업
            delay(2000L)
            inInitializing.value = false
        }
    }
}

@Composable
fun App(
    modifier: Modifier = Modifier,
    startDestination: AppDestination,
) {
    AppNavHost(
        startDestination = startDestination,
        modifier = modifier
    )
}

