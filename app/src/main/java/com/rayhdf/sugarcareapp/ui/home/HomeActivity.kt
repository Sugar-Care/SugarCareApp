package com.rayhdf.sugarcareapp.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rayhdf.sugarcareapp.ui.theme.SugarCareAppTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            SugarCareAppTheme {
                HomeScreen()
            }
        }
    }
}

