package com.example.ayush.kotlinstarter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by ayush on 7/5/17.
 */
interface ViewTypeDelegateAdapter {

    fun onCreateViewHolder(parent : ViewGroup) : RecyclerView.ViewHolder
    fun onBindViewHolder(holder : RecyclerView.ViewHolder?, item : ViewType)
}