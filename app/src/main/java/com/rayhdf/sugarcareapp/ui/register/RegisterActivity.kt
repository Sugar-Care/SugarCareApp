package com.rayhdf.sugarcareapp.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rayhdf.sugarcareapp.ui.composables.CustomTextField
import com.rayhdf.sugarcareapp.ui.home.HomeActivity
import com.rayhdf.sugarcareapp.ui.theme.SugarCareAppTheme

class RegisterActivity : ComponentActivity() {
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            RegisterScreen(viewModel = viewModel, onSuccess = { navigateToHome() })
        }

    }

    private fun navigateToHome() {
        val intent = Intent(this@RegisterActivity, HomeActivity::class.java)
        startActivity(intent)
    }
}

@Composable
fun RegisterScreen(viewModel: RegisterViewModel, onSuccess: () -> Unit) {
    var resultMessage by rememberSaveable { mutableStateOf("") }

    SugarCareAppTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            Text("Let's get started",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                label = "Name",
                value = viewModel.name,
                onValueChange = { viewModel.name = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                label = "Email",
                value = viewModel.email,
                onValueChange = { viewModel.email = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                label = "Password",
                value = viewModel.password,
                onValueChange = { viewModel.password = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = {
                    viewModel.registerUser(
                        onResult = { message -> resultMessage = message },
                        onSuccess = onSuccess
                    )
                }) {
                    Text("Register")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = resultMessage,
                fontWeight = FontWeight.Medium)
        }
    }
}

@Preview
@Composable
fun RegisterPreview() {
    val viewModel = RegisterViewModel()
    RegisterScreen(viewModel = viewModel, onSuccess = { })
}