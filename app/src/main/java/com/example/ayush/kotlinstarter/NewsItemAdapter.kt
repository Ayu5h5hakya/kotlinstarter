package com.example.ayush.kotlinstarter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.news_item.view.*

/**
 * Created by ayush on 7/5/17.
 */
class NewsItemAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = NewsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: ViewType) {
        holder as NewsViewHolder
        holder.bind(item as RedditNewsItem)
    }


    inner class NewsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.news_item)) {

        fun bind(item: RedditNewsItem) = itemView.apply{
            content.text = item.author
            profilePic.loadImage(item.thumbnail)
        }
    }
}