package com.rayhdf.sugarcareapp.ui.home.predict

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rayhdf.sugarcareapp.ui.ViewModelFactory
import com.rayhdf.sugarcareapp.ui.theme.SugarCareAppTheme

@Composable
fun PredictInputScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val viewModel: PredictInputViewModel = viewModel(factory = ViewModelFactory(context))

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

    val isLoading by viewModel.isLoading.collectAsState()

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
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp))
        {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text("Your Data", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold))
                Text("Leave empty if unknown")

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
                    item { Spacer(modifier = Modifier.height(8.dp)) }
                    item {
                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Button(onClick = {
                                viewModel.predict(
                                    onResult = { },
                                    onSuccess = {
                                        navController.navigate("Predict") {
                                            popUpTo("PredictInput") { inclusive = true }
                                        }
                                    }
                                )
                            }) {
                                Text("Confirm")
                            }
                        }
                    }
                }
            }

            if (isLoading) {
                CircularProgressIndicator()
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
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}
