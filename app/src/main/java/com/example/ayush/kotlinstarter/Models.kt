package com.example.ayush.kotlinstarter

/**
 * Created by ayush on 7/4/17.
 */

data class RedditNews(
        val after : String,
        val before : String,
        val news : List<RedditNewsItem>
)
data class RedditNewsItem(
   val author: String,
   val title : String,
   val num_comments : Int,
   val created : Long,
   val thumbnail : String,
   val url : String?
) : ViewType{

    override fun getViewType() = AdapterConstants.NEWS

}