package com.example.news_app_kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app_kotlin.models.Article
import com.example.news_app_kotlin.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repository = NewsRepository()

    val articles = MutableLiveData<List<Article>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    init {
        getTopHeadlines()
    }

    fun getTopHeadlines(country: String = "us") {
        loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.getTopHeadlines(country)
                if (response.isSuccessful) {
                    articles.value = response.body()?.articles ?: emptyList()
                    error.value = ""
                } else {
                    error.value = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                error.value = "Network error: ${e.message}"
            } finally {
                loading.value = false
            }
        }
    }

    fun searchNews(query: String) {
        if (query.isEmpty()) {
            getTopHeadlines()
            return
        }

        loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.searchNews(query)
                if (response.isSuccessful) {
                    articles.value = response.body()?.articles ?: emptyList()
                    error.value = ""
                } else {
                    error.value = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                error.value = "Network error: ${e.message}"
            } finally {
                loading.value = false
            }
        }
    }
}
