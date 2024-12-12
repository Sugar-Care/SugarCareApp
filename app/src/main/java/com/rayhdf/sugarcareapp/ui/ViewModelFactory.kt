package com.rayhdf.sugarcareapp.ui

import androidx.lifecycle.ViewModel
import com.rayhdf.sugarcareapp.ui.login.LoginViewModel
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.rayhdf.sugarcareapp.ui.home.first.FirstViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(context) as T
            modelClass.isAssignableFrom(FirstViewModel::class.java) -> FirstViewModel(context) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}