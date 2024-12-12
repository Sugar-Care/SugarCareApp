package com.rayhdf.sugarcareapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.rayhdf.sugarcareapp.data.model.TrackData
import com.rayhdf.sugarcareapp.data.model.TrackingItem
import com.rayhdf.sugarcareapp.ui.theme.SugarCareAppTheme



@Composable
fun TrackChart(chartTitle: String, trackingItems: List<TrackingItem>, valueSelector: (TrackData) -> Float?) {

    if(trackingItems.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "No data available", color = MaterialTheme.colorScheme.tertiary)
        }
        return
    }


    val dataPoints = trackingItems.mapIndexed { index, item ->
        Point(index.toFloat(), valueSelector(item.data ?: TrackData()) ?: 0f)
    }

    val steps = 5

    val yMin = dataPoints.minOf { it.y }
    val yMax = dataPoints.maxOf { it.y }
    val yRange = yMax - yMin
    val yStep = yRange / steps

    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.Transparent)
        .steps(dataPoints.size - 1)
        .labelData { i -> "${i + 1}" }
        .labelAndAxisLinePadding(20.dp) // Increased padding
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp) // Increased padding
        .labelData { i -> "%.1f".format(yMin + (i * yStep)) }
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = dataPoints,
                    LineStyle(
                        color = MaterialTheme.colorScheme.tertiary,
                        lineType = LineType.SmoothCurve(isDotted = false)
                    ),
                    IntersectionPoint(
                        color = MaterialTheme.colorScheme.tertiary
                    ),
                    SelectionHighlightPoint(color = MaterialTheme.colorScheme.primary),
                    ShadowUnderLine(
                        alpha = 0.5f,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.inversePrimary,
                                Color.Transparent
                            )
                        )
                    ),
                    SelectionHighlightPopUp()
                )
            )
        ),
        backgroundColor = MaterialTheme.colorScheme.surface,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(color = MaterialTheme.colorScheme.outline)
    )

    Box(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)) {

        LineChart(
            modifier = Modifier.fillMaxWidth().height(300.dp).align(Alignment.BottomCenter),
            lineChartData = lineChartData
        )
        Text(
            text = chartTitle,
            modifier = Modifier.align(Alignment.TopStart).padding(start = 8.dp),
            color = MaterialTheme.colorScheme.tertiary
        )
        Text(
            text = "Days",
            modifier = Modifier.align(Alignment.BottomEnd).padding(8.dp).background(MaterialTheme.colorScheme.background),
            color = MaterialTheme.colorScheme.tertiary
        )

    }

}

//@Preview
//@Composable
//fun TrackChartPreview(modifier: Modifier = Modifier) {
//    SugarCareAppTheme {
//        TrackChart("Weight")
//    }
//
//}