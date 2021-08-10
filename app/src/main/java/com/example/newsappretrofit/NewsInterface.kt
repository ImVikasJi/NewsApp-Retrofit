package com.example.newsappretrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=in&apiKey=API_KEY
//https://newsapi.org/v2/everything?domains=wsj.com&apiKey=API_KEY

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "6242edab390a4186aaffaadcee4b60a7"
interface NewsInterface {

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadLines(@Query("country")country: String, @Query("page")page : Int): Call<News>
}
object NewsService{
    val newsInstance: NewsInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}

//https://newsapi.org/v2/top-headlines?apiKey=$API_KEY&country=in&page=1