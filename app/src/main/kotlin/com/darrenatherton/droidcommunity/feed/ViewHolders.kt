package com.darrenatherton.droidcommunity.feed

import android.support.v7.widget.RecyclerView
import android.view.View
import com.darrenatherton.droidcommunity.feed.entity.FeedViewItem

sealed class FeedListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class RedditItemViewHolder(itemView: View) : FeedListViewHolder(itemView) {

        fun bind(item: FeedViewItem.Reddit) {

        }
    }

    class SimpleItemViewHolder(itemView: View) : FeedListViewHolder(itemView)
}