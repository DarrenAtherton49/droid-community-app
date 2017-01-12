package com.darrenatherton.droidcommunity.features.feed.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.darrenatherton.droidcommunity.R
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.features.feed.FeedListViewHolder
import com.darrenatherton.droidcommunity.features.feed.entity.SubscriptionViewItem
import java.util.*

@PerScreen
class FeedListAdapter constructor(private var subscriptionViewItems: List<SubscriptionViewItem> = Collections.emptyList()) :
        RecyclerView.Adapter<FeedListViewHolder>() {

    private val onItemClickListeners: MutableList<OnItemClickListener> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedListViewHolder {
        return when (viewType) {
            SubscriptionViewItem.redditItemGroup -> {
                //todo inflate layout representing reddit tile group
                FeedListViewHolder.RedditGroup(LayoutInflater.from(parent.context).inflate(R.layout.item_feed_group_reddit, parent, false))
            }
            SubscriptionViewItem.twitterItemGroup -> {
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
            is FeedListViewHolder.RedditGroup -> viewHolder.bind(getItem(position) as SubscriptionViewItem.Reddit)
            is FeedListViewHolder.TwitterGroup -> viewHolder.bind(getItem(position) as SubscriptionViewItem.Twitter)
        }
    }

    override fun getItemCount() = subscriptionViewItems.size

    override fun getItemViewType(position: Int) = getItem(position).viewType

    internal fun replaceData(newSubscriptions: List<SubscriptionViewItem>) {
        subscriptionViewItems = newSubscriptions
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = subscriptionViewItems[position]

    internal fun addOnItemClickListener(onItemClickListener: OnItemClickListener) {
        onItemClickListeners.add(onItemClickListener)
    }

    private fun notifyOnFeedItemClicked(subscriptionViewItem: SubscriptionViewItem) {
        onItemClickListeners.forEach { it.onFeedItemClicked(subscriptionViewItem) }
    }

    interface OnItemClickListener {
        fun onFeedItemClicked(subscriptionViewItem: SubscriptionViewItem)
    }
}