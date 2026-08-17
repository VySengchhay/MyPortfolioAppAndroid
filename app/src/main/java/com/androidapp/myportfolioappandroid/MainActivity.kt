package com.androidapp.myportfolioappandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.androidapp.myportfolioappandroid.core.ui.theme.MyPortfolioAppAndroidTheme
import com.androidapp.myportfolioappandroid.feature.auth.AuthViewModel
import com.androidapp.myportfolioappandroid.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyPortfolioAppAndroidTheme {
                val navController = rememberNavController()
                val authViewModel : AuthViewModel by viewModels()

                AppNavHost(
                    navController,
                    authViewModel
                )
            }
        }
    }
}
