package com.example.newsappretrofit

import android.icu.text.CaseMap

data class Article(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String
)