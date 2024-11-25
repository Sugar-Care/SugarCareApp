package com.rayhdf.sugarcareapp.ui.home.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rayhdf.sugarcareapp.ui.composables.CustomTextField
import com.rayhdf.sugarcareapp.ui.theme.SugarCareAppTheme
import com.rayhdf.sugarcareapp.ui.theme.primaryLight

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, viewModel: ProfileViewModel = viewModel()) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Profile Icon",
            modifier = Modifier.size(48.dp)
        )
        Text("Your Name", fontSize = 24.sp,
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(color = primaryLight)
        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = "Email",
        )

        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = "Password",
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {},
            ) {
                Text("Save")
            }
        }

    }
}

@Preview
@Composable
fun PreviewProfile() {
    SugarCareAppTheme {
        ProfileScreen()
    }
}