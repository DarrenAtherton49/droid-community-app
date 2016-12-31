package com.darrenatherton.droidcommunity.features.feed.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.darrenatherton.droidcommunity.R
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.features.feed.RedditItemViewHolder
import com.darrenatherton.droidcommunity.features.feed.entity.FeedViewSingleItem
import java.util.*

@PerScreen
class RedditGroupAdapter constructor(private var redditItems: List<FeedViewSingleItem.Reddit> = Collections.emptyList()) :
        RecyclerView.Adapter<RedditItemViewHolder>() {

    private val onItemClickListeners: MutableList<OnItemClickListener> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditItemViewHolder {
        return RedditItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_feed_reddit, parent, false))
    }

    override fun onBindViewHolder(viewHolder: RedditItemViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    override fun getItemCount() = redditItems.size

    override fun getItemViewType(position: Int) = 0

    internal fun replaceData(newItems: List<FeedViewSingleItem.Reddit>) {
        redditItems = newItems
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = redditItems[position]

    internal fun addOnItemClickListener(onItemClickListener: OnItemClickListener) {
        onItemClickListeners.add(onItemClickListener)
    }

    private fun notifyOnFeedItemClicked(redditItem: FeedViewSingleItem.Reddit) {
        onItemClickListeners.forEach { it.onRedditItemClicked(redditItem) }
    }

    interface OnItemClickListener {
        fun onRedditItemClicked(redditItem: FeedViewSingleItem.Reddit)
    }
}