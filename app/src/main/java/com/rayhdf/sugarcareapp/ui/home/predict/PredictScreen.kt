package com.rayhdf.sugarcareapp.ui.home.predict

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rayhdf.sugarcareapp.ui.composables.PredictionHistory
import com.rayhdf.sugarcareapp.ui.theme.errorLight
import com.rayhdf.sugarcareapp.ui.theme.primaryLight
import com.rayhdf.sugarcareapp.ui.theme.surfaceContainerHighestLightHighContrast

@Preview
@Composable
fun PredictScreen(modifier: Modifier = Modifier) {

    val predictItems = listOf(
        PredictItem("1-10-2019", "Healthy"),
        PredictItem("1-09-2019", "Healthy"),
        PredictItem("1-08-2019", "At risk")
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier.fillMaxSize().padding(top = 16.dp)
    ) {
        LatestPrediction()
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Prediction", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add",
                modifier = Modifier.clickable {
                // TBA
                 })
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(predictItems) { item ->
                 PredictionHistory(
                    resultText = item.result,
                    dateText = item.date,
                    color = if (item.result == "Healthy") primaryLight else errorLight
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

data class PredictItem(
    val date: String,
    val result: String,
)

@Composable
fun LatestPrediction(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = surfaceContainerHighestLightHighContrast)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Latest",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = primaryLight
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "at 1-10-2019",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "You are healthy",
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                color = primaryLight
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Please keep it up and stay healthy!",
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )
        }
    }
}