package com.darrenatherton.droidcommunity.feed

import android.support.v7.widget.RecyclerView
import android.view.View
import com.darrenatherton.droidcommunity.feed.entity.FeedViewItem
import kotlinx.android.synthetic.main.item_feed_reddit.view.*

sealed class FeedListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class RedditItemViewHolder(itemView: View) : FeedListViewHolder(itemView) {

        fun bind(item: FeedViewItem.Reddit) {
            with(item) {
                itemView.title_textview.text = title
                itemView.subreddit_textview.text = title
            }
        }
    }

    class SimpleItemViewHolder(itemView: View) : FeedListViewHolder(itemView)
}