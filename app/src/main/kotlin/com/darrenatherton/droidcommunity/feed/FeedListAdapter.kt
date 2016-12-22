package com.darrenatherton.droidcommunity.feed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.darrenatherton.droidcommunity.R
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.feed.reddit.entity.FeedItem
import java.util.*

@PerScreen
class FeedListAdapter constructor(private var feedItems: List<FeedItem> = Collections.emptyList()) :
        RecyclerView.Adapter<FeedListAdapter.FeedListViewHolder>() {

    private val onItemClickListeners: MutableList<OnItemClickListener> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedListViewHolder {
        return FeedListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false))
    }

    override fun onBindViewHolder(holder: FeedListViewHolder, position: Int) {
        val feedItem: FeedItem = getItem(position)
    }

    override fun getItemCount() = feedItems.size

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    internal fun replaceData(newItems: List<FeedItem>) {
        feedItems = newItems
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = feedItems[position]

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