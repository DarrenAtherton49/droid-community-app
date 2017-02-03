package com.darrenatherton.droidcommunity.features.feed.adapter

import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.darrenatherton.droidcommunity.R
import com.darrenatherton.droidcommunity.base.presentation.BaseRecyclerWithChildAdapter
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.features.feed.FeedItemPresenter
import com.darrenatherton.droidcommunity.features.feed.SubscriptionViewHolder
import com.darrenatherton.droidcommunity.features.feed.entity.SubscriptionViewItem
import kotlinx.android.synthetic.main.item_subscription.view.*
import java.util.*
import javax.inject.Inject

@PerScreen
class FeedListAdapter @Inject constructor(feedItemPresenter: FeedItemPresenter) :
        BaseRecyclerWithChildAdapter<SubscriptionViewHolder, FeedItemPresenter.View,
                FeedItemPresenter, SubscriptionViewItem>(), FeedItemPresenter.View {

    override val passiveView = this
    override val presenter: FeedItemPresenter = feedItemPresenter

    private val onItemClickListeners: MutableList<OnItemClickListener> = ArrayList()

    // Maps of child RecyclerView adapters and state to restore
    private lateinit var childAdapters: HashMap<String, FeedChildAdapter<*, *>> //todo check projection syntax
    private lateinit var childAdapterStates: HashMap<String, Parcelable>

    init {
        setUpChildAdapters(items)
    }

    // Cache the scroll position of the session list so that we can restore it when re-binding.
    override fun onViewRecycled(holder: SubscriptionViewHolder) {
        val position = holder.adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            val key = getItem(position).key
            when (holder) {
                is SubscriptionViewHolder.Reddit, is SubscriptionViewHolder.Twitter -> {
                    childAdapterStates.put(key, holder.itemView.childRecyclerView
                            .layoutManager.onSaveInstanceState())
                }
            }
        }
        super.onViewRecycled(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionViewHolder {
        return when (viewType) {
            SubscriptionViewItem.redditItem -> {
                //todo change to use pattern for managing viewholder instead of creating and binding inside viewholder
                SubscriptionViewHolder.Reddit.Factory.create(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_subscription, parent, false))
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
            is SubscriptionViewHolder.Reddit -> {
                //todo change to use pattern for managing viewholder instead of creating and binding inside viewholder
                val item = getItem(position) as SubscriptionViewItem.Reddit
                viewHolder.bind(item, childAdapters[item.key], childAdapterStates[item.key])
            }
            is SubscriptionViewHolder.Twitter -> viewHolder.bind(getItem(position) as SubscriptionViewItem.Twitter)
        }
    }

    override fun getItemViewType(position: Int) = getItem(position).viewType

    private fun setUpChildAdapters(subscriptions: List<SubscriptionViewItem>) {
        childAdapters = HashMap(subscriptions.size)
        childAdapterStates = HashMap(subscriptions.size)

        // store an adapter for each subscription
        subscriptions.forEach {
            when (it) {
                is SubscriptionViewItem.Reddit -> childAdapters.put(it.key, FeedRedditChildAdapter())
                //todo add 'is SubscriptionViewItem.Twitter -> childAdapters.put(it.key, FeedTwitterChildAdapter())
            }
        }
    }

    internal fun replaceData(newSubscriptions: List<SubscriptionViewItem>) {
        items = newSubscriptions
        //todo check if changed before executing the code below
        setUpChildAdapters(newSubscriptions)
        notifyDataSetChanged() //todo change to use DiffUtils
    }

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