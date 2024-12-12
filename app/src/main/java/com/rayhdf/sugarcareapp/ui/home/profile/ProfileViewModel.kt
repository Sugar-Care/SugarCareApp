package com.rayhdf.sugarcareapp.ui.home.profile

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rayhdf.sugarcareapp.data.preferences.UserPreference
import com.rayhdf.sugarcareapp.data.preferences.dataStore
import kotlinx.coroutines.launch

class ProfileViewModel(context: Context) : ViewModel() {
    private val userPreference = UserPreference.getInstance(context.dataStore)

    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    init {
        viewModelScope.launch {
            userPreference.getSession().collect { user ->
                name = user.name
                email = user.email
            }
        }
    }
}