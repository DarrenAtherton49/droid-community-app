package com.darrenatherton.droidcommunity.features.feed.adapter

import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.darrenatherton.droidcommunity.R
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.features.feed.SubscriptionFeedItem
import com.darrenatherton.droidcommunity.features.feed.SubscriptionViewHolder
import com.darrenatherton.droidcommunity.features.feed.SubscriptionViewItem
import kotlinx.android.synthetic.main.item_subscription.view.*
import java.util.*
import javax.inject.Inject

/**
 * This class is a RecyclerView adapter whose items also have (nested) RecyclerView adapters. Each
 * item in this adapter represents a subscription and the nested (horizontal RecyclerView in that
 * subscription represents items which belong to that subscription.
 */
@PerScreen
class FeedListAdapter @Inject constructor() : RecyclerView.Adapter<SubscriptionViewHolder>() {

    private var items: List<SubscriptionViewItem> = ArrayList()

    private val onItemClickListeners: MutableList<OnItemClickListener> = ArrayList()

    // Maps of child RecyclerView adapters and state to restore
    private lateinit var childAdapters: HashMap<String, FeedChildAdapter<*, *>> //todo check projection syntax
    private lateinit var childAdapterStates: HashMap<String, Parcelable>

    init {
        setUpChildAdapters(items)
    }

    //===================================================================================
    // Initialization
    //===================================================================================

    private fun setUpChildAdapters(subscriptions: List<SubscriptionViewItem>) {
        childAdapters = HashMap(subscriptions.size)
        childAdapterStates = HashMap(subscriptions.size)

        // store an adapter for each subscription
        subscriptions.forEach {
            with(it) {
                when (subscription) {
                    is SubscriptionFeedItem.Reddit -> {
                        childAdapters.put(subscription.key, FeedRedditChildAdapter())
                    }
                    is SubscriptionFeedItem.Twitter -> {
                        //todo add 'is SubscriptionFeedItem.Twitter
                    }
                }
            }
        }
    }

    //===================================================================================
    // RecyclerView.Adapter lifecycle/functions
    //===================================================================================

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionViewHolder {
        return when (viewType) {
            SubscriptionFeedItem.redditItem -> {
                //todo change to use pattern for managing viewholder instead of creating and binding inside viewholder
                SubscriptionViewHolder.Reddit.Factory.create(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_subscription, parent, false))
            }
            SubscriptionFeedItem.twitter -> {
                SubscriptionViewHolder.Twitter(LayoutInflater.from(parent.context).inflate(R.layout.item_subscription, parent, false))
            }
            else -> {
                SubscriptionViewHolder.Simple(LayoutInflater.from(parent.context).inflate(R.layout.item_subscription, parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: SubscriptionViewHolder, position: Int) {
        when (holder) {
            is SubscriptionViewHolder.Reddit -> {
                //todo change to use pattern for managing viewholder instead of creating and binding inside viewholder
                val item = getSubscription(position) as SubscriptionFeedItem.Reddit
                holder.bind(item, childAdapters[item.key], childAdapterStates[item.key])
            }
            is SubscriptionViewHolder.Twitter -> holder.bind(getSubscription(position) as SubscriptionFeedItem.Twitter)
        }
    }

    override fun getItemViewType(position: Int) = getSubscription(position).viewType

    override fun getItemCount() = items.size

    // Cache the scroll position of the session list so that we can restore it when re-binding.
    override fun onViewRecycled(holder: SubscriptionViewHolder) {

        val position = holder.adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            val key = getSubscription(position).key
            when (holder) {
                is SubscriptionViewHolder.Reddit, is SubscriptionViewHolder.Twitter -> {
                    childAdapterStates.put(key, holder.itemView.childRecyclerView
                            .layoutManager.onSaveInstanceState())
                }
            }
        }

        super.onViewRecycled(holder)
    }

    internal fun replaceData(newSubscriptions: List<SubscriptionViewItem>) {
        items = newSubscriptions
        //todo check if changed before executing the code below
        setUpChildAdapters(newSubscriptions)
        notifyDataSetChanged() //todo change to use DiffUtils
    }

    private fun getSubscription(position: Int) = items[position].subscription

    private fun getSubscriptionChildren(position: Int) = items[position].subscriptionItems

    //===================================================================================
    // Click listener
    //===================================================================================

    internal fun addOnItemClickListener(onItemClickListener: OnItemClickListener) {
        onItemClickListeners.add(onItemClickListener)
    }

    private fun notifyOnFeedItemClicked(subscriptionFeedItem: SubscriptionFeedItem) {
        onItemClickListeners.forEach { it.onSubscriptionItemClicked(subscriptionFeedItem) }
    }

    interface OnItemClickListener {
        fun onSubscriptionItemClicked(subscriptionFeedItem: SubscriptionFeedItem)
    }
}