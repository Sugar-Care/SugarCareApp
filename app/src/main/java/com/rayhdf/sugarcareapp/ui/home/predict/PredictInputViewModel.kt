package com.rayhdf.sugarcareapp.ui.home.predict

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PredictInputViewModel : ViewModel() {
    var age by mutableStateOf("")
}