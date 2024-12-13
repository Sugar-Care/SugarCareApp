package com.rayhdf.sugarcareapp.ui.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rayhdf.sugarcareapp.data.repository.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var passwordVisible by mutableStateOf(false)

    private val userRepository = UserRepository()

    fun registerUser(onResult: (String) -> Unit, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = userRepository.registerUser(name, email, password)
                if (response.message == "User registered successfully") {
                    onSuccess()
                } else {
                    onResult(response.message ?: "Registration failed")
                }
            } catch (e: Exception) {
                onResult("Registration failed: ${e.message}")
            }
        }
    }
}