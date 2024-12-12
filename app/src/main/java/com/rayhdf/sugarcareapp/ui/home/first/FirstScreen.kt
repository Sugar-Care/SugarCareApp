package com.rayhdf.sugarcareapp.ui.home.first

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rayhdf.sugarcareapp.ui.ViewModelFactory
import com.rayhdf.sugarcareapp.ui.home.predict.LatestPrediction
import com.rayhdf.sugarcareapp.ui.theme.SugarCareAppTheme

@Composable
fun FirstScreen(modifier: Modifier = Modifier, viewModel: FirstViewModel = viewModel(factory = ViewModelFactory(
    LocalContext.current)
)) {
    val name by viewModel.name.observeAsState("")

    LazyColumn(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 32.dp)
    ) {
        item {
            Text(
                "Greetings,",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Normal),
            )
        }
        item {
            Text(
                name,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Medium),
            )
        }
        item { Spacer(modifier = Modifier.height(24.dp)) }
        item {
            Text(
                "Recent activities",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Normal),
            )
        }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            "Sugar Intake",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .padding(16.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add blood sugar data button",
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable {
                                    // TODO: 'Add' blood sugar function
                                }
                        )
                    }
                }
            }
        }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item {
            Text(
                "Latest news on diabetes",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium)
            )
        }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item { NewsCard() }
    }
}

@Composable
fun NewsCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            )
                        )
                    )
            )

            Text(
                text = "News Title",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFirstScreen(modifier: Modifier = Modifier) {
    SugarCareAppTheme {
        FirstScreen()
    }
}