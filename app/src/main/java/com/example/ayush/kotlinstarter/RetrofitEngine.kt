package com.example.ayush.kotlinstarter

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by ayush on 7/6/17.
 */
class RetrofitEngine {
    private val redditApi : RedditInterface

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        redditApi = retrofit.create(RedditInterface::class.java)
    }

    fun getNews(after: String,limit: String) : Observable<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }
}