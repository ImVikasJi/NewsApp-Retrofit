package com.example.newsappretrofit

data class News(
    val totalResults: Int,
    val articles: List<Article>
)