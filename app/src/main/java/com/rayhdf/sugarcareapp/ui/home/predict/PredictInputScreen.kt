package com.rayhdf.sugarcareapp.ui.home.predict

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.rayhdf.sugarcareapp.ui.theme.SugarCareAppTheme

@Composable
fun PredictInputScreen(
    viewModel: PredictInputViewModel
) {
    SugarCareAppTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text("Your Data", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold))
            Text("Please input your latest data")

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Age", style = MaterialTheme.typography.labelLarge)
                TextField(
                    value = viewModel.age,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { viewModel.age = it },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PredictInputScreenPreview(modifier: Modifier = Modifier) {
    val viewModel = PredictInputViewModel()
    PredictInputScreen(viewModel)
}