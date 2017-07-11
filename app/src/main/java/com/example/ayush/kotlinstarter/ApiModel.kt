package com.example.ayush.kotlinstarter

/**
 * Created by ayush on 7/6/17.
 */

class RedditNewsResponse(val data : RedditDataResponse)

class RedditDataResponse(
        val children: List<RedditChildrenResponse>,
        val after : String?,
        val before : String?
)

class RedditChildrenResponse(val data : RedditNewsDataResponse)

data class RedditNewsDataResponse(
        val author: String,
        val title : String,
        val num_comments : Int,
        val created: Long,
        val thumbnail : String,
        val url : String
) : ViewType{
    override fun getViewType() = AdapterConstants.NEWS

}