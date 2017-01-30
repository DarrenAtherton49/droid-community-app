package com.darrenatherton.droidcommunity.features.feed.entity

//===================================================================================
// Entities for top level feed items (e.g. horizontal list of reddit posts
// from a subreddit or list of tweets)
//===================================================================================

sealed class SubscriptionViewItem(val key: String, val title: String, val order: Int, val viewType: Int) {
    class Reddit(key: String, title: String, order: Int) : SubscriptionViewItem(key, title, order, redditItem)
    class Twitter(key: String, title: String, order: Int) : SubscriptionViewItem(key, title, order, twitter)
    //class Dribbble() : Subscription() etc...

    companion object ViewType {
        val redditItem by lazy { 0 }
        val twitter by lazy { 1 }
    }
}

//===================================================================================
// Entities for individual feed tiles
//===================================================================================

sealed class FeedViewSingleItem {

    class Reddit(val title: String,
                 val subreddit: String,
                 val author: String,
                 val submitted: String,
                 val numComments: String)
        : FeedViewSingleItem()

    class Twitter(title: String) : FeedViewSingleItem()
}
