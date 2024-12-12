package com.rayhdf.sugarcareapp.ui.home.first

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rayhdf.sugarcareapp.data.preferences.UserPreference
import com.rayhdf.sugarcareapp.data.preferences.dataStore
import kotlinx.coroutines.launch

class FirstViewModel(context: Context) : ViewModel() {
    private val userPreference = UserPreference.getInstance(context.dataStore)

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    init {
        viewModelScope.launch {
            userPreference.getSession().collect() { user ->
                _name.value = user.name
            }
        }
    }
}