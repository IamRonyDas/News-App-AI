package com.example.news_app_kotlin.repository

import com.example.news_app_kotlin.Constant.ApiKey
import com.example.news_app_kotlin.models.NewsResponse
import com.example.news_app_kotlin.network.RetrofitInstance
import retrofit2.Response

class NewsRepository {

    private val api = RetrofitInstance.api
    private val apiKey = ApiKey.NEWS_API_KEY

    suspend fun getTopHeadlines(country: String = "us"): Response<NewsResponse> {
        return api.getTopHeadlines(country, apiKey)
    }

    suspend fun searchNews(query: String): Response<NewsResponse> {
        return api.searchNews(query, apiKey)
    }
}
