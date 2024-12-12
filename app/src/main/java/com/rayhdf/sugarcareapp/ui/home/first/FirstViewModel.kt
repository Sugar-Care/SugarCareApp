package com.rayhdf.sugarcareapp.ui.home.first

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rayhdf.sugarcareapp.BuildConfig
import com.rayhdf.sugarcareapp.data.model.ArticlesItem
import com.rayhdf.sugarcareapp.data.model.NewsResponse
import com.rayhdf.sugarcareapp.data.preferences.UserPreference
import com.rayhdf.sugarcareapp.data.preferences.dataStore
import com.rayhdf.sugarcareapp.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FirstViewModel(context: Context) : ViewModel() {
    private val userPreference = UserPreference.getInstance(context.dataStore)
    private val userRepository = UserRepository()

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _news = MutableStateFlow<List<ArticlesItem>>(emptyList())
    val news: StateFlow<List<ArticlesItem>> = _news

    private val apiKey = BuildConfig.newsKey

    init {
        viewModelScope.launch {
            userPreference.getSession().collect() { user ->
                _name.value = user.name
            }
        }
    }

    fun getNews() {
        viewModelScope.launch {
            try {
                val response = userRepository.getNews("diabetes", apiKey, 1)
                Log.d("News API", "response: $response")
                _news.value = response.articles?.firstOrNull()?.let { listOf(it) } ?: emptyList()
            } catch (e: Exception) {
                Log.d("News API", "Error: $e")
            }

        }
    }
}