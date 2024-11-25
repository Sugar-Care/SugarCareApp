package com.rayhdf.sugarcareapp.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.rayhdf.sugarcareapp.ui.theme.primaryLight
import com.rayhdf.sugarcareapp.ui.theme.surfaceContainerHighestLightHighContrast

@Composable
fun PredictionHistory(
    modifier: Modifier = Modifier,
    resultText: String,
    dateText: String,
    color: Color = primaryLight
) {
    Card(
        modifier = modifier
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = surfaceContainerHighestLightHighContrast)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = dateText,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = resultText,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                color = color,
                modifier = Modifier.weight(2f)
            )
        }
    }
}

@Preview
@Composable
fun PreviewHistory() {
    Column {
        PredictionHistory(Modifier, "At risk", "20-10-2019")
        PredictionHistory(Modifier, "Healthy", "1-10-2019")
    }
}