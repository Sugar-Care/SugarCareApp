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

    private val userRepository = UserRepository()

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

    fun predict(onResult: (String) -> Unit, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
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