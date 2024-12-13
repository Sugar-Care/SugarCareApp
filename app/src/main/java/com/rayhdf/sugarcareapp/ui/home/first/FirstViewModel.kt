package com.rayhdf.sugarcareapp.ui.home.first

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rayhdf.sugarcareapp.BuildConfig
import com.rayhdf.sugarcareapp.data.model.ArticlesItem
import com.rayhdf.sugarcareapp.data.model.TrackingItem
import com.rayhdf.sugarcareapp.data.preferences.UserPreference
import com.rayhdf.sugarcareapp.data.preferences.dataStore
import com.rayhdf.sugarcareapp.data.repository.UserRepository
import com.rayhdf.sugarcareapp.ui.home.track.TrackViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FirstViewModel(context: Context) : ViewModel() {
    private val userPreference = UserPreference.getInstance(context.dataStore)
    private val userRepository = UserRepository()

    private val trackViewModel = TrackViewModel(context)

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _news = MutableStateFlow<List<ArticlesItem>>(emptyList())
    val news: StateFlow<List<ArticlesItem>> = _news

    private val _tracks = trackViewModel.tracks
    val tracks: StateFlow<List<TrackingItem>> = _tracks

    private val _recentTracks = MutableStateFlow<List<TrackingItem>>(emptyList())
    val recentTracks: StateFlow<List<TrackingItem>> = _recentTracks

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val apiKey = BuildConfig.newsKey

    init {
        viewModelScope.launch {
            userPreference.getSession().collect { user ->
                _name.value = user.name
            }
        }
    }

    fun getTracks() {
        viewModelScope.launch {
            trackViewModel.getTracks()
            trackViewModel.recentTracks.collectLatest { tracks ->
                _recentTracks.value = tracks.take(5)
            }
            Log.d("Home Screen" ,"$recentTracks")
        }
    }

    fun getNews() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = userRepository.getNews("diabetes", apiKey, 1)
                Log.d("News API", "response: $response")
                _news.value = response.articles?.firstOrNull()?.let { listOf(it) } ?: emptyList()
            } catch (e: Exception) {
                Log.d("News API", "Error: $e")
            } finally {
                _isLoading.value = false
            }
        }
    }
}