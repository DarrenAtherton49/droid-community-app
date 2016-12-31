package com.darrenatherton.droidcommunity.features.feed.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.darrenatherton.droidcommunity.R
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.features.feed.FeedListViewHolder
import com.darrenatherton.droidcommunity.features.feed.entity.FeedViewGroupItem
import java.util.*

@PerScreen
class FeedListAdapter constructor(private var feedViewGroupItems: List<FeedViewGroupItem> = Collections.emptyList()) :
        RecyclerView.Adapter<FeedListViewHolder>() {

    private val onItemClickListeners: MutableList<OnItemClickListener> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedListViewHolder {
        return when (viewType) {
            FeedViewGroupItem.redditItemGroup ->  {
                //todo inflate layout representing reddit tile group
                FeedListViewHolder.RedditGroup(LayoutInflater.from(parent.context).inflate(R.layout.item_feed_reddit, parent, false))
            }
            FeedViewGroupItem.twitterItemGroup -> {
                //todo inflate layout representing twitter tile group
                FeedListViewHolder.TwitterGroup(LayoutInflater.from(parent.context).inflate(R.layout.item_feed_twitter, parent, false))
            }
            else -> {
                FeedListViewHolder.SimpleItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false))
            }
        }
    }

    override fun onBindViewHolder(viewHolder: FeedListViewHolder, position: Int) {
        when (viewHolder) {
            is FeedListViewHolder.RedditGroup -> viewHolder.bind(getItem(position) as FeedViewGroupItem.Reddit)
            is FeedListViewHolder.TwitterGroup -> viewHolder.bind(getItem(position) as FeedViewGroupItem.Twitter)
        }
    }

    override fun getItemCount() = feedViewGroupItems.size

    override fun getItemViewType(position: Int) = getItem(position).viewType

    internal fun replaceData(newViewGroupItems: List<FeedViewGroupItem>) {
        feedViewGroupItems = newViewGroupItems
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = feedViewGroupItems[position]

    internal fun addOnItemClickListener(onItemClickListener: OnItemClickListener) {
        onItemClickListeners.add(onItemClickListener)
    }

    private fun notifyOnFeedItemClicked(feedViewGroupItem: FeedViewGroupItem) {
        onItemClickListeners.forEach { it.onFeedItemClicked(feedViewGroupItem) }
    }

    interface OnItemClickListener {
        fun onFeedItemClicked(feedViewGroupItem: FeedViewGroupItem)
    }
}