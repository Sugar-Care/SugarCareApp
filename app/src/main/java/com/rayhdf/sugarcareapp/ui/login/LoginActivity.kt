package com.rayhdf.sugarcareapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rayhdf.sugarcareapp.ui.home.HomeActivity
import com.rayhdf.sugarcareapp.ui.register.RegisterActivity
import com.rayhdf.sugarcareapp.ui.theme.SugarCareAppTheme
import com.rayhdf.sugarcareapp.ui.theme.primaryLight
import com.rayhdf.sugarcareapp.ui.theme.tertiaryLight


class LoginActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(
                viewModel = viewModel,
                onLoginClick = { navigateToHome() },
                onSignUpClick = { navigateToRegister() }
            )
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
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    SugarCareAppTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text(
                "Log In",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Medium)
            )
            Text(
                "Sugar Care",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = primaryLight
                )
            )
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Logo",
                tint = tertiaryLight,
                modifier = Modifier.size(48.dp)
            )

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 8.dp)
            ) {
                Text("Email")
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = viewModel.email,
                    onValueChange = { viewModel.email = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 8.dp)
            ) {
                Text("Password")
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.password = it },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (viewModel.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (viewModel.passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        IconButton(onClick = { viewModel.passwordVisible = !viewModel.passwordVisible }) {
                            Icon(imageVector = image, contentDescription = null)
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Forgot Password?",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Normal,
                            color = Color.Transparent
                        )
                    )
                }
            }
            Button(onClick = onLoginClick) {
                Text("Log In")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text("Don't have an account? ", style = MaterialTheme.typography.bodyMedium)
                Text(
                    "Sign Up",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.clickable(onClick = onSignUpClick)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val viewModel = LoginViewModel()
    LoginScreen(viewModel = viewModel, {}, {})
}