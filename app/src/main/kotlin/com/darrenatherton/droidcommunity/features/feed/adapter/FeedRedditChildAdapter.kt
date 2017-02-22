package com.darrenatherton.droidcommunity.features.feed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.darrenatherton.droidcommunity.R
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.features.feed.RedditFeedItemViewHolder
import com.darrenatherton.droidcommunity.features.feed.FeedSingleViewItem
import java.util.*
import javax.inject.Inject

@PerScreen
class FeedRedditChildAdapter @Inject constructor() :
        FeedChildAdapter<RedditFeedItemViewHolder, FeedSingleViewItem.Reddit>() {

    private val onItemClickListeners: MutableList<OnItemClickListener> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditFeedItemViewHolder {
        return RedditFeedItemViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_feed_reddit, parent, false))
    }

    override fun onBindViewHolder(viewHolder: RedditFeedItemViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = 0

    internal fun addOnItemClickListener(onItemClickListener: OnItemClickListener) {
        onItemClickListeners.add(onItemClickListener)
    }

    private fun notifyOnFeedItemClicked(redditItem: FeedSingleViewItem.Reddit) {
        onItemClickListeners.forEach { it.onRedditItemClicked(redditItem) }
    }

    interface OnItemClickListener {
        fun onRedditItemClicked(redditItem: FeedSingleViewItem.Reddit)
    }
}