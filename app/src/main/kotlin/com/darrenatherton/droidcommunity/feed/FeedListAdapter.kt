package com.darrenatherton.droidcommunity.feed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.darrenatherton.droidcommunity.R
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.feed.entity.FeedViewItem
import java.util.*

@PerScreen
class FeedListAdapter constructor(private var feedViewItems: List<FeedViewItem> = Collections.emptyList()) :
        RecyclerView.Adapter<FeedListAdapter.FeedListViewHolder>() {

    private val onItemClickListeners: MutableList<OnItemClickListener> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedListViewHolder {
        return FeedListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false))
    }

    override fun onBindViewHolder(holder: FeedListViewHolder, position: Int) {
        val feedViewItem: FeedViewItem = getItem(position)
    }

    override fun getItemCount() = feedViewItems.size

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    internal fun replaceData(newViewItems: List<FeedViewItem>) {
        feedViewItems = newViewItems
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = feedViewItems[position]

    class FeedListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    internal fun addOnItemClickListener(onItemClickListener: OnItemClickListener) {
        onItemClickListeners.add(onItemClickListener)
    }

    private fun notifyOnFeedItemClicked(feedViewItem: FeedViewItem) {
        onItemClickListeners.forEach { it.onFeedItemClicked(feedViewItem) }
    }

    interface OnItemClickListener {
        fun onFeedItemClicked(feedViewItem: FeedViewItem)
    }
}