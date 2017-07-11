package com.example.ayush.kotlinstarter

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ayush on 7/6/17.
 */
interface RedditInterface {

    @GET("/top.json")
    fun getTop(@Query("after") after : String, @Query("limit") limit : String) : Observable<RedditNewsResponse>
}