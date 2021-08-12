package com.example.newsappretrofit

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import kotlin.math.log

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    lateinit var adapter: NewsAdapter
    private var articles = mutableListOf<Article>()
    var pageNum = 1
    var totalResults = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = NewsAdapter(this@MainActivity, articles)
        rvNewsList.adapter = adapter
        //  rvNewsList.layoutManager = LinearLayoutManager(this@MainActivity)

        val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(2000)
        layoutManager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener {
            override fun onItemChanged(position: Int) {
                rvContainer.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))
                Log.d(TAG, "First Visible item - ${layoutManager.getFirstVisibleItemPosition()}")
                Log.d(TAG, "Total count - ${layoutManager.itemCount}")
                if (totalResults > layoutManager.itemCount && layoutManager.getFirstVisibleItemPosition() >= layoutManager.itemCount - 5) {
                    pageNum++
                    getNews()
                }
            }
        })
        rvNewsList.layoutManager = layoutManager
        getNews()
    }

    private fun getNews() {
        Log.d(TAG, "Request sent for $pageNum")
        val news = NewsService.newsInstance.getHeadLines("in", pageNum)
        news.enqueue(object : retrofit2.Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    Log.d(TAG, news.toString())
                    totalResults = news.totalResults
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d(TAG, "Error in fetching News", t)
            }
        })
    }
}

