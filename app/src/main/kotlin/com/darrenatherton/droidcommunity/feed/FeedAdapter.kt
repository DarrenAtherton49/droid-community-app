package com.darrenatherton.droidcommunity.feed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.darrenatherton.droidcommunity.R
import java.util.*
import javax.inject.Inject

class FeedListAdapter @Inject constructor() : RecyclerView.Adapter<FeedListAdapter.FeedListViewHolder>() {

    private val onItemClickListeners: MutableList<OnItemClickListener> = ArrayList()
    private val feedItemList: MutableList<FeedItem> = Collections.emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedListViewHolder {
        return FeedListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false))
    }

    override fun onBindViewHolder(holder: FeedListViewHolder, position: Int) {
        //todo
    }

    override fun getItemCount() = feedItemList.count()

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    class FeedListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    internal fun addOnItemClickListener(onItemClickListener: OnItemClickListener) {
        onItemClickListeners.add(onItemClickListener)
    }

    private fun notifyOnFeedItemClicked(feedItem: FeedItem) {
        onItemClickListeners.forEach { it.onFeedItemClicked(feedItem) }
    }

    interface OnItemClickListener {
        fun onFeedItemClicked(feedItem: FeedItem)
    }
}