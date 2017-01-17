package com.darrenatherton.droidcommunity.features.feed

import android.support.v7.widget.RecyclerView
import android.view.View
import com.darrenatherton.droidcommunity.features.feed.entity.FeedViewSingleItem
import com.darrenatherton.droidcommunity.features.feed.entity.SubscriptionViewItem
import kotlinx.android.synthetic.main.include_subscription_header.view.*

//===================================================================================
// Top level subscription ViewHolders (e.g. horizontal list of reddit posts
// from a subreddit or list of tweets)
//===================================================================================

sealed class SubscriptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class Reddit(itemView: View) : SubscriptionViewHolder(itemView) {

        fun bind(item: SubscriptionViewItem.Reddit) {
            with(item) {
                itemView.subscriptionTitle.text = title
            }
        }
    }

    class Twitter(itemView: View) : SubscriptionViewHolder(itemView) {

        fun bind(item: SubscriptionViewItem.Twitter) {
            with(item) {

            }
        }
    }

    class Simple(itemView: View) : SubscriptionViewHolder(itemView)
}

//===================================================================================
// ViewHolders for individual feed tiles
//===================================================================================

class RedditItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: FeedViewSingleItem.Reddit) {
        with(item) {
//            itemView.itemTitle.text = title
//            itemView.author_when_textview.text = author //todo concatenate 'submitted' - e.g. 10h ago
//            itemView.num_comments_textview.text = numComments //todo concatenate 'comments' to number
        }
    }
}