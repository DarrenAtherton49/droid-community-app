package com.darrenatherton.droidcommunity.features.feed

import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.darrenatherton.droidcommunity.features.feed.adapter.FeedChildAdapter
import com.darrenatherton.droidcommunity.features.feed.FeedSingleViewItem
import com.darrenatherton.droidcommunity.features.feed.SubscriptionFeedItem
import kotlinx.android.synthetic.main.include_subscription_header.view.*
import kotlinx.android.synthetic.main.item_subscription.view.*

//===================================================================================
// Top level subscription ViewHolders (e.g. horizontal list of reddit posts
// from a subreddit or list of tweets)
//===================================================================================

sealed class SubscriptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class Reddit(itemView: View) : SubscriptionViewHolder(itemView) {

        object Factory {
            fun create(itemView: View): SubscriptionViewHolder.Reddit {
                val holder = SubscriptionViewHolder.Reddit(itemView)
                itemView.childRecyclerView.setHasFixedSize(true)
                // The line below ensures that child RecyclerView does not capture vertical scrolls,
                // as we want the outer RecyclerView to handle those instead.
                itemView.childRecyclerView.isNestedScrollingEnabled = false
                val layoutManager = LinearLayoutManager(itemView.context)
                layoutManager.setInitialPrefetchItemCount(5)//todo set based on orientation
                itemView.childRecyclerView.layoutManager = layoutManager
                //todo itemView.childRecyclerView.setRecyclerViewPool()
                return holder
            }
        }

        fun bind(item: SubscriptionFeedItem.Reddit, childAdapter: FeedChildAdapter<*, *>?,
                 childAdapterState: Parcelable?) {
            with(item) {

                itemView.subscriptionTitle.text = title
                itemView.subscriptionHeader.contentDescription = title
                itemView.childRecyclerView.adapter = childAdapter
                itemView.childRecyclerView.layoutManager.onRestoreInstanceState(childAdapterState)
            }
        }
    }

    class Twitter(itemView: View) : SubscriptionViewHolder(itemView) {

        fun bind(item: SubscriptionFeedItem.Twitter) {
            with(item) {

            }
        }
    }

    class Simple(itemView: View) : SubscriptionViewHolder(itemView)
}

//===================================================================================
// ViewHolders for individual feed tiles
//===================================================================================

class RedditFeedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: FeedSingleViewItem.Reddit) {
        with(item) {
//            itemView.itemTitle.text = title
//            itemView.author_when_textview.text = author //todo concatenate 'submitted' - e.g. 10h ago
//            itemView.num_comments_textview.text = numComments //todo concatenate 'comments' to number
        }
    }
}