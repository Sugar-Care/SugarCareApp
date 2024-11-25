package com.rayhdf.sugarcareapp.ui.home.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    var email by mutableStateOf("example@gmail.com")
    var password by mutableStateOf("********")
}