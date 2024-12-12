package com.rayhdf.sugarcareapp.ui.home.predict

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rayhdf.sugarcareapp.data.model.PredictionsItem
import com.rayhdf.sugarcareapp.data.preferences.UserPreference
import com.rayhdf.sugarcareapp.data.preferences.dataStore
import com.rayhdf.sugarcareapp.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class PredictViewModel(context: Context) : ViewModel() {

    private val userRepository =  UserRepository()
    private val userPreference = UserPreference.getInstance(context.dataStore)

    private val _predictions = MutableStateFlow<List<PredictionsItem>>(emptyList())
    val predictions: StateFlow<List<PredictionsItem>> = _predictions

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    @RequiresApi(Build.VERSION_CODES.O)
    fun getPredictions() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val user = userPreference.getSession().first()
                val userId = user.userId
                Log.d("Get Predictions", "id: $userId")
                if (userId.isNotEmpty()) {
                    val response = withContext(Dispatchers.IO) {
                        userRepository.getPredictions(userId)
                    }
                    val sortedPredictions = response.predictions
                        ?.filterNotNull()
                        ?.sortedByDescending { it.data?.createdAt?.let { date -> ZonedDateTime.parse(date) } }
                        ?: emptyList()
                    _predictions.value = sortedPredictions
                    Log.d("Get Predict", "$response")
                } else {
                    Log.d("Get Predict", "User ID is empty")
                }
            } catch (e: Exception) {
                Log.d("Get Predict", "Error: $e")
            } finally {
                _isLoading.value = false
            }
        }
    }
}