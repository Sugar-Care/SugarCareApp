package com.rayhdf.sugarcareapp.ui.home.track

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.rayhdf.sugarcareapp.ui.ViewModelFactory
import com.rayhdf.sugarcareapp.ui.composables.TrackChart
import com.rayhdf.sugarcareapp.ui.theme.SugarCareAppTheme

@Composable
fun TrackScreen(modifier: Modifier, ) {
    val context = LocalContext.current
    val viewModel: TrackViewModel = viewModel(factory = ViewModelFactory(context))
    val recentTracks by viewModel.recentTracks.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val recommendations by viewModel.recommendations.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getTracks()
    }

    if (viewModel.showDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.showDialog = false },
            title = { Text(text = "Track Data") },
            text = {
                Column {
                    TextField(
                        value = viewModel.sugarIntake,
                        onValueChange = { viewModel.sugarIntake = it },
                        label = { Text("Sugar Intake") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = viewModel.weight,
                        onValueChange = { viewModel.weight = it },
                        label = { Text("Weight") }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.track(
                            onResult = { message -> Log.d("Predict screen", "$message")},
                            onSuccess = { viewModel.showDialog = false}
                        )
                    }
                ) {
                    Text("Submit")
                }
            },
            dismissButton = {
                Button(
                    onClick = { viewModel.showDialog = false }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = modifier
                .fillMaxSize()
                .padding(top = 32.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 8.dp)
            ) {
                Text("Track", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Medium))
            }
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
            ) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
                    ) {
                        Box(
                            contentAlignment = Alignment.TopStart,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            TrackChart("Sugar Intake", recentTracks) { it.sugarIntake?.toFloat() }
                        }
                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
                    ) {
                        TrackChart("Weight", recentTracks) { it.bodyWeight?.toFloat() }
                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    Row {
                        Button(
                            modifier = Modifier,
                            onClick = { viewModel.showDialog = true }
                        ) { Text("Track") }
                        Spacer(Modifier.width(16.dp))
                        Button(
                            modifier = Modifier,
                            onClick = { viewModel.getRecommendations() }
                        ) { Text("Get Recommendations") }
                    }
                }
                item {
                    recommendations?.let {
                        Text("Recommendations: $it", modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview
@Composable
fun PreviewTrackScreen() {
    SugarCareAppTheme { TrackScreen(Modifier) }

}