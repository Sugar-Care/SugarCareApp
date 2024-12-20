package com.rayhdf.sugarcareapp.ui

import androidx.lifecycle.ViewModel
import com.rayhdf.sugarcareapp.ui.login.LoginViewModel
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.rayhdf.sugarcareapp.data.preferences.UserPreference
import com.rayhdf.sugarcareapp.ui.home.first.FirstViewModel
import com.rayhdf.sugarcareapp.ui.home.predict.PredictInputViewModel
import com.rayhdf.sugarcareapp.ui.home.predict.PredictViewModel
import com.rayhdf.sugarcareapp.ui.home.profile.ProfileViewModel
import com.rayhdf.sugarcareapp.ui.home.track.TrackViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(context) as T
            modelClass.isAssignableFrom(FirstViewModel::class.java) -> FirstViewModel(context) as T
            modelClass.isAssignableFrom(PredictViewModel::class.java) -> PredictViewModel(context) as T
            modelClass.isAssignableFrom(TrackViewModel::class.java) -> TrackViewModel(context) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(context) as T
            modelClass.isAssignableFrom(PredictInputViewModel::class.java) -> PredictInputViewModel(context) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}