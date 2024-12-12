package com.rayhdf.sugarcareapp.ui.home.predict

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rayhdf.sugarcareapp.data.model.PredictRequest
import com.rayhdf.sugarcareapp.data.repository.UserRepository
import kotlinx.coroutines.launch

class PredictInputViewModel(
) : ViewModel() {
    var age by mutableStateOf("")
    var bloodGlucoseLevels by mutableStateOf("")
    var bloodPressure by mutableStateOf("")
    var weightGainDuringPregnancy by mutableStateOf("")
    var waistCircumference by mutableStateOf("")
    var bmi by mutableStateOf("")
    var insulinLevels by mutableStateOf("")
    var cholesterolLevels by mutableStateOf("")
    var digestiveEnzymeLevels by mutableStateOf("")
    var pulmonaryFunction by mutableStateOf("")

    private val userRepository = UserRepository()

    fun toPredictRequest(): PredictRequest {
        return PredictRequest(
            input = PredictRequest.Input(
                age = age.toIntOrNull() ?: 18,
                bloodGlucoseLevels = bloodGlucoseLevels.toDoubleOrNull() ?: 1.0,
                bloodPressure = bloodPressure.toDoubleOrNull() ?: 1.0,
                weightGainDuringPregnancy = weightGainDuringPregnancy.toDoubleOrNull() ?: 1.0,
                waistCircumference = waistCircumference.toDoubleOrNull() ?: 1.0,
                bmi = bmi.toDoubleOrNull() ?: 1.0,
                insulinLevels = insulinLevels.toDoubleOrNull() ?: 1.0,
                cholesterolLevels = cholesterolLevels.toDoubleOrNull() ?: 1.0,
                digestiveEnzymeLevels = digestiveEnzymeLevels.toDoubleOrNull() ?: 1.0,
                pulmonaryFunction = pulmonaryFunction.toDoubleOrNull() ?: 1.0
            )
        )
    }

    fun predict(onResult: (String) -> Unit, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                Log.d("Predict", "${toPredictRequest()}")
                val userId = "MOkL2e0ZDUf8zboWxZvc"
                val response = userRepository.predict(userId, toPredictRequest())
                if (response.result?.message == "Prediction stored successfully") {
                    Log.d("Predict", "$response bang")
                    onSuccess()
                }
            } catch (e: Exception) {
                onResult("Predict Failed: ${e.message}")
            }
        }
    }


}