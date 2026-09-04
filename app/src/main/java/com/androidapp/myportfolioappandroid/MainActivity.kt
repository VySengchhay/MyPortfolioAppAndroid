package com.androidapp.myportfolioappandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.androidapp.myportfolioappandroid.core.service.fms.NotificationPermissionRequest
import com.androidapp.myportfolioappandroid.core.ui.loading.LoadingContent
import com.androidapp.myportfolioappandroid.core.ui.theme.MyPortfolioAppAndroidTheme
import com.androidapp.myportfolioappandroid.core.util.LoadingUtil
import com.androidapp.myportfolioappandroid.feature.splashscreen.SplashScreenViewModel
import com.androidapp.myportfolioappandroid.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val splashScreenViewModel : SplashScreenViewModel by lazy {
        ViewModelProvider(this@MainActivity)[SplashScreenViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                splashScreenViewModel.isSplashScreenVisible.value
            }
        }

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            NotificationPermissionRequest()

            MyPortfolioAppAndroidTheme {
                val navController = rememberNavController()

                if (LoadingUtil.isLoading.value) {
                    LoadingContent()
                }

                AppNavHost(
                    navController
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        intent.getStringExtra("route")
        println("=====> onNewIntent route: ${intent.getStringExtra("route")}")
    }
}

