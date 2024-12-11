package com.rayhdf.sugarcareapp.ui.home.predict

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rayhdf.sugarcareapp.ui.theme.SugarCareAppTheme

@Composable
fun PredictInputScreen(
    viewModel: PredictInputViewModel,
    navController: NavController
) {
    var age by rememberSaveable { mutableStateOf(viewModel.age) }
    var bloodGlucoseLevels by rememberSaveable { mutableStateOf(viewModel.bloodGlucoseLevels) }
    var bloodPressure by rememberSaveable { mutableStateOf(viewModel.bloodPressure) }
    var weightGainDuringPregnancy by rememberSaveable { mutableStateOf(viewModel.weightGainDuringPregnancy) }
    var waistCircumference by rememberSaveable { mutableStateOf(viewModel.waistCircumference) }
    var bmi by rememberSaveable { mutableStateOf(viewModel.bmi) }
    var insulinLevels by rememberSaveable { mutableStateOf(viewModel.insulinLevels) }
    var cholesterolLevels by rememberSaveable { mutableStateOf(viewModel.cholesterolLevels) }
    var digestiveEnzymeLevels by rememberSaveable { mutableStateOf(viewModel.digestiveEnzymeLevels) }
    var pulmonaryFunction by rememberSaveable { mutableStateOf(viewModel.pulmonaryFunction) }

    LaunchedEffect(age, bloodGlucoseLevels, bloodPressure, weightGainDuringPregnancy, waistCircumference, bmi, insulinLevels, cholesterolLevels, digestiveEnzymeLevels, pulmonaryFunction) {
        viewModel.age = age
        viewModel.bloodGlucoseLevels = bloodGlucoseLevels
        viewModel.bloodPressure = bloodPressure
        viewModel.weightGainDuringPregnancy = weightGainDuringPregnancy
        viewModel.waistCircumference = waistCircumference
        viewModel.bmi = bmi
        viewModel.insulinLevels = insulinLevels
        viewModel.cholesterolLevels = cholesterolLevels
        viewModel.digestiveEnzymeLevels = digestiveEnzymeLevels
        viewModel.pulmonaryFunction = pulmonaryFunction
    }

    SugarCareAppTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            Text("Your Data", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold))
            Text("Please input your latest data")

            LazyColumn(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                item { TextFieldWithLabel("Age", age) { age = it } }
                item { TextFieldWithLabel("Blood Glucose Levels", bloodGlucoseLevels) { bloodGlucoseLevels = it } }
                item { TextFieldWithLabel("Blood Pressure", bloodPressure) { bloodPressure = it } }
                item { TextFieldWithLabel("Weight Gain During Pregnancy", weightGainDuringPregnancy) { weightGainDuringPregnancy = it } }
                item { TextFieldWithLabel("Waist Circumference", waistCircumference) { waistCircumference = it } }
                item { TextFieldWithLabel("BMI", bmi) { bmi = it } }
                item { TextFieldWithLabel("Insulin Levels", insulinLevels) { insulinLevels = it } }
                item { TextFieldWithLabel("Cholesterol Levels", cholesterolLevels) { cholesterolLevels = it } }
                item { TextFieldWithLabel("Digestive Enzyme Levels", digestiveEnzymeLevels) { digestiveEnzymeLevels = it } }
                item { TextFieldWithLabel("Pulmonary Function", pulmonaryFunction) { pulmonaryFunction = it } }
                item { Button(onClick = {
                    viewModel.predict(
                        onResult = { },
                        onSuccess = { navController.navigate("PredictResult") }
                    )
                }) {
                    Text("Confirm")
                } }
            }
        }
    }
}

@Composable
fun TextFieldWithLabel(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
            .padding(top = 4.dp)
    ) {
        Text(label, style = MaterialTheme.typography.labelLarge)
        TextField(
            value = value,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onValueChange,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}
