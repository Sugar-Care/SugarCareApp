package com.rayhdf.sugarcareapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rayhdf.sugarcareapp.ui.theme.errorLight
import com.rayhdf.sugarcareapp.ui.theme.onSurfaceLight
import com.rayhdf.sugarcareapp.ui.theme.primaryLight
import com.rayhdf.sugarcareapp.ui.theme.surfaceContainerHighestLightHighContrast

@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelWidth: Dp = 80.dp,
    enabled: Boolean = true,
    isError: Boolean = false,
    singleLine: Boolean = true
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = surfaceContainerHighestLightHighContrast,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .width(labelWidth)
                .padding(end = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isError) {
                    errorLight
                } else if (!enabled) {
                    onSurfaceLight.copy(alpha = 0.38f)
                } else {
                    onSurfaceLight
                },
                maxLines = 1
            )
        }

        TextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            isError = isError,
            singleLine = singleLine,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = surfaceContainerHighestLightHighContrast,
                focusedContainerColor = surfaceContainerHighestLightHighContrast,
                disabledContainerColor = surfaceContainerHighestLightHighContrast,
                errorContainerColor = surfaceContainerHighestLightHighContrast,
                unfocusedIndicatorColor = surfaceContainerHighestLightHighContrast,
                focusedIndicatorColor = primaryLight,
                errorIndicatorColor = errorLight,
            ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier
                .weight(1f)
                .heightIn(min = 48.dp)
        )
    }
}

@Preview
@Composable
fun CustomFieldPreview() {
    CustomTextField(
        label = "Age",
        value = "20 years old",
        onValueChange = {}
    )
}