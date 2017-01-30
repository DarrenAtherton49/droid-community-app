package com.darrenatherton.droidcommunity.features.feed.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.darrenatherton.droidcommunity.R
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.features.feed.SubscriptionViewHolder
import com.darrenatherton.droidcommunity.features.feed.entity.SubscriptionViewItem
import java.util.*

@PerScreen
class FeedListAdapter constructor(private var subscriptionViewItems: List<SubscriptionViewItem> = Collections.emptyList()) :
        RecyclerView.Adapter<SubscriptionViewHolder>() {

    private val onItemClickListeners: MutableList<OnItemClickListener> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionViewHolder {
        return when (viewType) {
            SubscriptionViewItem.redditItem -> {
                SubscriptionViewHolder.Reddit(LayoutInflater.from(parent.context).inflate(R.layout.item_subscription, parent, false))
            }
            SubscriptionViewItem.twitter -> {
                SubscriptionViewHolder.Twitter(LayoutInflater.from(parent.context).inflate(R.layout.item_subscription, parent, false))
            }
            else -> {
                SubscriptionViewHolder.Simple(LayoutInflater.from(parent.context).inflate(R.layout.item_subscription, parent, false))
            }
        }
    }

    override fun onBindViewHolder(viewHolder: SubscriptionViewHolder, position: Int) {
        when (viewHolder) {
            is SubscriptionViewHolder.Reddit -> viewHolder.bind(getItem(position) as SubscriptionViewItem.Reddit)
            is SubscriptionViewHolder.Twitter -> viewHolder.bind(getItem(position) as SubscriptionViewItem.Twitter)
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
        onItemClickListeners.forEach { it.onSubscriptionItemClicked(subscriptionViewItem) }
    }

    interface OnItemClickListener {
        fun onSubscriptionItemClicked(subscriptionViewItem: SubscriptionViewItem)
    }
}