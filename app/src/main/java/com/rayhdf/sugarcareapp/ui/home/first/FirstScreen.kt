package com.rayhdf.sugarcareapp.ui.home.first

import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.Glide
import com.rayhdf.sugarcareapp.data.model.ArticlesItem
import com.rayhdf.sugarcareapp.ui.ViewModelFactory
import com.rayhdf.sugarcareapp.ui.composables.TrackChart
import com.rayhdf.sugarcareapp.ui.home.predict.LatestPrediction
import com.rayhdf.sugarcareapp.ui.theme.SugarCareAppTheme

@Composable
fun FirstScreen(modifier: Modifier = Modifier, viewModel: FirstViewModel = viewModel(factory = ViewModelFactory(
    LocalContext.current)
)) {
    val name by viewModel.name.observeAsState("")
    val news by viewModel.news.collectAsState()
    val recentTracks by viewModel.recentTracks.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getNews()
        viewModel.getTracks()
    }

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
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item {
            Text(
                "Latest news on diabetes",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium)
            )
        }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        if (news.isEmpty()) {
            item {
                Text(
                    "No News Today",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else {
            items(news) { article ->
                article.let {
                    NewsCard(news = it)
                }
            }
        }
    }
}

@Composable
fun NewsCard(news: ArticlesItem, modifier: Modifier = Modifier) {
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
            AndroidView(
                factory = { context ->
                    ImageView(context).apply {
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }
                },
                update = { imageView ->
                    Glide.with(imageView.context)
                        .load(news.urlToImage)
                        .into(imageView)
                }
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
                text = news.title ?: "No Title",
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