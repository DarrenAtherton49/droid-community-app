package com.darrenatherton.droidcommunity.features.feed

//===================================================================================
// Entities for top level feed items (e.g. horizontal list of reddit posts
// from a subreddit or list of tweets)
//===================================================================================

sealed class SubscriptionFeedItem(val key: String, val title: String, val order: Int, val viewType: Int) {
    class Reddit(key: String, title: String, order: Int) : SubscriptionFeedItem(key, title, order, redditItem)
    class Twitter(key: String, title: String, order: Int) : SubscriptionFeedItem(key, title, order, twitter)
    //class Dribbble() : Subscription() etc...

    companion object ViewType {
        val redditItem by lazy { 0 }
        val twitter by lazy { 1 }
    }
}

//===================================================================================
// Entities for individual feed tiles
//===================================================================================

sealed class FeedSingleViewItem {

    class Reddit(val title: String,
                 val subreddit: String,
                 val author: String,
                 val submitted: String,
                 val numComments: String)
        : FeedSingleViewItem()

    class Twitter(title: String) : FeedSingleViewItem()
}

//===================================================================================
// Object used to contain a subscription and its items. The subscription feed
// take a list of this object, sorted by subscription.order
//===================================================================================

data class SubscriptionViewItem(val subscription: SubscriptionFeedItem, val subscriptionItems: List<FeedSingleViewItem>)