package com.rayhdf.sugarcareapp.ui.home.predict

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rayhdf.sugarcareapp.data.model.PredictionsItem
import com.rayhdf.sugarcareapp.ui.ViewModelFactory
import com.rayhdf.sugarcareapp.ui.composables.PredictionHistory
import com.rayhdf.sugarcareapp.ui.formatDate
import com.rayhdf.sugarcareapp.ui.theme.errorLight
import com.rayhdf.sugarcareapp.ui.theme.primaryLight
import com.rayhdf.sugarcareapp.ui.theme.surfaceContainerHighestLightHighContrast

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PredictScreen(modifier: Modifier = Modifier, navController: NavController) {
    val context = LocalContext.current
    val viewModel: PredictViewModel = viewModel(factory = ViewModelFactory(context))
    val predictions by viewModel.predictions.collectAsState()
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getPredictions()
        viewModel.isLoading.collect { loading ->
            isLoading = loading
        }
    }

    val recentPrediction = predictions.firstOrNull()

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize().padding(top = 16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 8.dp)
            ) {
                Text("Predict", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Medium))
            }
            LatestPrediction(recentPrediction)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Prediction", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add",
                    modifier = Modifier.clickable {
                        navController.navigate("PredictInput")
                    })
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(predictions) { item ->
                    PredictionHistory(
                        resultText = item.data?.prediction?.label ?: "Unknown",
                        dateText = item.data?.createdAt?.let { formatDate(it) } ?: "Unknown",
                        color = if (item.data?.prediction?.probability!! > 0.7) errorLight else primaryLight
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LatestPrediction(topPrediction: PredictionsItem?, modifier: Modifier = Modifier) {
    val probability = topPrediction?.data?.prediction?.probability ?: 0f
    val labelColor = if (probability > 0.7) errorLight else primaryLight
    val noteText = if (probability > 0.7) "Please consult a doctor!" else "Please keep it up and stay healthy!"

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
                text = "at ${topPrediction?.data?.createdAt?.let { formatDate(it) } ?: "Unknown"}",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = topPrediction?.data?.prediction?.label ?: "Unknown",
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                color = labelColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = noteText,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )
        }
    }
}