package com.darrenatherton.droidcommunity.feed.shared

import android.support.v7.widget.RecyclerView
import android.view.View
import com.darrenatherton.droidcommunity.feed.shared.entity.FeedViewItem
import kotlinx.android.synthetic.main.item_feed_reddit.view.*

sealed class FeedListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class RedditItemViewHolder(itemView: View) : FeedListViewHolder(itemView) {

        fun bind(item: FeedViewItem.Reddit) {
            with(item) {
                itemView.title_textview.text = title
                itemView.subreddit_textview.text = subreddit
                itemView.author_when_textview.text = author //todo concatenate 'submitted' - e.g. 10h ago
                itemView.num_comments_textview.text = numComments //todo concatenate 'comments' to number
            }
        }
    }

    class TwitterItemViewHolder(itemView: View) : FeedListViewHolder(itemView) {

        fun bind(item: FeedViewItem.Twitter) {
            with(item) {

            }
        }
    }

    class SimpleItemViewHolder(itemView: View) : FeedListViewHolder(itemView)
}