package com.example.news_app_kotlin.models

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
