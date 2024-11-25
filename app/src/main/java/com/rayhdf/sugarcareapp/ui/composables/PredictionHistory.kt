package com.rayhdf.sugarcareapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PredictionHistory(
    modifier: Modifier = Modifier,
    resultText: String,
    dateText: String,
    color: Color
) {
    Box(
        modifier = Modifier
            .background(Color.White)

    ) {
        Column(

        ) {
            Text(text = resultText, fontWeight = FontWeight.Medium, fontSize = 24.sp, color = color)
            Text(text = dateText)
        }
    }

}

@Preview
@Composable
fun PreviewHistory() {
    PredictionHistory(Modifier, "At risk", "20-10-2019", Color.DarkGray)
}