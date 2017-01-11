package com.darrenatherton.droidcommunity.features.feed

import android.support.v7.widget.RecyclerView
import android.view.View
import com.darrenatherton.droidcommunity.features.feed.entity.SubscriptionViewItem
import com.darrenatherton.droidcommunity.features.feed.entity.FeedViewSingleItem
import kotlinx.android.synthetic.main.item_feed_reddit.view.*

//===================================================================================
// Top level feed item ViewHolders (e.g. horizontal list of reddit posts
// from a subreddit or list of tweets)
//===================================================================================

sealed class FeedListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class RedditGroup(itemView: View) : FeedListViewHolder(itemView) {

        fun bind(item: SubscriptionViewItem.Reddit) {
            with(item) {
                itemView.title_textview.text = title
            }
        }
    }

    class TwitterGroup(itemView: View) : FeedListViewHolder(itemView) {

        fun bind(item: SubscriptionViewItem.Twitter) {
            with(item) {

            }
        }
    }

    class SimpleItemViewHolder(itemView: View) : FeedListViewHolder(itemView)
}

//===================================================================================
// ViewHolders for individual feed tiles
//===================================================================================

class RedditItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: FeedViewSingleItem.Reddit) {
        with(item) {
            itemView.title_textview.text = title
            itemView.subreddit_textview.text = subreddit
            itemView.author_when_textview.text = author //todo concatenate 'submitted' - e.g. 10h ago
            itemView.num_comments_textview.text = numComments //todo concatenate 'comments' to number
        }
    }
}