package com.rayhdf.sugarcareapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.rayhdf.sugarcareapp.ui.theme.SugarCareAppTheme
import com.rayhdf.sugarcareapp.ui.composables.SignInWithGoogleButton
import com.rayhdf.sugarcareapp.ui.home.HomeActivity
import com.rayhdf.sugarcareapp.ui.register.RegisterActivity
import com.rayhdf.sugarcareapp.ui.theme.primaryLight


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(onSignInClick = { navigateToRegister() }, onSignIn2Click = { navigateToHome() })
        }
    }

    private fun navigateToRegister() {
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToHome() {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        startActivity(intent)
    }
}

@Composable
fun LoginScreen(onSignInClick: () -> Unit, onSignIn2Click: () -> Unit) {
    SugarCareAppTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Welcome to", fontSize = 40.sp, textAlign = TextAlign.Center)
            Text(
                "Sugar Care",
                fontSize = 60.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = primaryLight
            )
            Text("Login Activity", color = Color.Transparent)
            SignInWithGoogleButton(onClick = onSignInClick)

            Text("v Langsung ke home v")
            SignInWithGoogleButton(onClick = onSignIn2Click)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginScreen(onSignInClick = {}, onSignIn2Click = {})
}