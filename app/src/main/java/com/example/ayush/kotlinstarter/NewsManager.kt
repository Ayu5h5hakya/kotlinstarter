package com.example.ayush.kotlinstarter

import io.reactivex.Observable

/**
 * Created by ayush on 7/6/17.
 */
class NewsManager(val api : RetrofitEngine= RetrofitEngine()) {

    fun getNews(limit : String = "10") : Observable<RedditNewsResponse> {
        return api.getNews("",limit)
    }
}