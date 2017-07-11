package com.example.ayush.kotlinstarter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by ayush on 7/5/17.
 */
class NewsLoadingAdapter : ViewTypeDelegateAdapter{
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = LoaderViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: ViewType) {

    }

    inner class LoaderViewHolder(parent : ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.news_item_loading)){}
}