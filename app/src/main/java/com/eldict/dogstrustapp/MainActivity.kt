package com.eldict.dogstrustapp

// The only activity (screen container) in the app. It sets up the theme and hands everything to AppNavigation.

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.eldict.dogstrustapp.navigation.AppNavigation
import com.eldict.dogstrustapp.ui.theme.DogsTrustAppTheme
import android.graphics.Color
import androidx.activity.SystemBarStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge( // for status bar
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT,
                Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT,
                Color.TRANSPARENT)
        )
        setContent {
            DogsTrustAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}