package com.rayhdf.sugarcareapp.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("example@gmail.com")
    var password by mutableStateOf("********")
    var passwordVisible by mutableStateOf(false)
}