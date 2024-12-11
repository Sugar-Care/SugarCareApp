package com.rayhdf.sugarcareapp.ui.home.predict

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.rayhdf.sugarcareapp.data.model.PredictRequest

class PredictInputViewModel(
) : ViewModel() {
    var age by mutableStateOf("18")
    var bloodGlucoseLevels by mutableStateOf("1.0")
    var bloodPressure by mutableStateOf("1.0")
    var weightGainDuringPregnancy by mutableStateOf("1.0")
    var waistCircumference by mutableStateOf("1.0")
    var bmi by mutableStateOf("1.0")
    var insulinLevels by mutableStateOf("1.0")
    var cholesterolLevels by mutableStateOf("1.0")
    var digestiveEnzymeLevels by mutableStateOf("1.0")
    var pulmonaryFunction by mutableStateOf("1.0")

    fun toPredictRequest(): PredictRequest {
        return PredictRequest(
            input = PredictRequest.Input(
                age = age.toInt(),
                bloodGlucoseLevels = bloodGlucoseLevels.toDouble(),
                bloodPressure = bloodPressure.toDouble(),
                weightGainDuringPregnancy = weightGainDuringPregnancy.toDouble(),
                waistCircumference = waistCircumference.toDouble(),
                bmi = bmi.toDouble(),
                insulinLevels = insulinLevels.toDouble(),
                cholesterolLevels = cholesterolLevels.toDouble(),
                digestiveEnzymeLevels = digestiveEnzymeLevels.toDouble(),
                pulmonaryFunction = pulmonaryFunction.toDouble()
            )
        )
    }
}