package com.rayhdf.sugarcareapp.ui.login

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rayhdf.sugarcareapp.data.model.UserModel
import com.rayhdf.sugarcareapp.data.preferences.UserPreference
import com.rayhdf.sugarcareapp.data.preferences.dataStore
import com.rayhdf.sugarcareapp.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LoginViewModel(private val context: Context) : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordVisible by mutableStateOf(false)

    private val userRepository = UserRepository()
    private val userPreference = UserPreference.getInstance(context.dataStore)

    fun login(onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = userRepository.loginUser(email, password)
                if(response.error == false && response.message == "success") {
                    response.loginResult?.let { loginResult ->
                        val user = UserModel(
                            name = loginResult.name ?: "",
                            email = loginResult.email ?: "",
                            userId = loginResult.userId?: "",
                        )
                        userPreference.saveSession(user)
                    }
                    Log.d("Login API", "${response.loginResult}")
                    onSuccess()
                } else {
                    onError("Error logging in")
                }
            } catch (e: Exception) {
                onError("Login Failed: ${e.message}")
            }
        }
    }

    fun getUserSession(): Flow<UserModel> {
        return userPreference.getSession()
    }
}