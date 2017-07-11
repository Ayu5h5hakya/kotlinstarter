package com.example.ayush.kotlinstarter

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.ViewGroup

/**
 * Created by ayush on 7/4/17.
 */
class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>

    private var delegateAdapters = SparseArray<ViewTypeDelegateAdapter>()

    private val loadingItem = object : ViewType {
        override fun getViewType(): Int = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, NewsLoadingAdapter())
        delegateAdapters.put(AdapterConstants.NEWS, NewsItemAdapter())
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) = delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, items[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = delegateAdapters.get(viewType).onCreateViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int) = items[position].getViewType()

    fun addNews(listing : List<RedditChildrenResponse>){

        val initPosition = items.size - 1
        items.remove(loadingItem)
        notifyItemRemoved(initPosition)

        val listingData  = listing.map {
            val item = it.data
            RedditNewsItem (item.author,item.title,item.num_comments,item.created,item.thumbnail,item.url)
        }
        items.addAll(listingData)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size+1)

    }

}