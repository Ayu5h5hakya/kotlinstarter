package com.example.ayush.kotlinstarter

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val newsListing: RecyclerView by lazy {
        idRecycler
    }

    private val newsManager by lazy {
        NewsManager()
    }

    private var redditNews: RedditNews? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar

        newsListing.setHasFixedSize(true)
        var linearlayout = LinearLayoutManager(this)
        newsListing.layoutManager = linearlayout
        newsListing.clearOnScrollListeners()
        newsListing.addOnScrollListener(InfiniteScrollListener({ requestNews() }, linearlayout))
        newsListing.adapter = NewsAdapter()

        requestNews()

        logExecution("Testing") { requestNews() }

        var(_, status) = function()


    }

    fun requestNews() {
        val subscription = newsManager.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    retrievedNews ->
                    (newsListing.adapter as NewsAdapter).addNews(retrievedNews.data.children)
                }, {
                    e ->
                    Snackbar.make(newsListing, e.message ?: "", Snackbar.LENGTH_SHORT).show()
                })

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    fun logExecution(tag: String, func: () -> Unit) {
        func()
    }

    data class Result(val data :String, val status : Int)

    fun  function() : Result {

        var testing = listOf(1,2,3,4,5,6)
        if (testing.any { it % 2 == 0 } && testing.all { it < 10 }){
            var count = testing.count { it % 2 == 0 }
        }

        testing.forEach {  }

        return Result("success", 200)
    }

}
