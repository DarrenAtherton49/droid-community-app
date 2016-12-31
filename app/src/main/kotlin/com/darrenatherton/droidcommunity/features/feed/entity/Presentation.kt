package com.darrenatherton.droidcommunity.features.feed.entity

import com.darrenatherton.droidcommunity.reddit.entity.Subreddit

//===================================================================================
// Entities for top level feed items (e.g. horizontal list of reddit posts
// from a subreddit or list of tweets)
//===================================================================================

sealed class FeedViewGroupItem(val title: String, val viewType: Int) {

    class Reddit(title: String, subreddit: Subreddit) : FeedViewGroupItem(title, redditItemGroup)
    class Twitter(title: String) : FeedViewGroupItem(title, twitterItemGroup)

    companion object Type {
        val redditItemGroup by lazy { 0 }
        val twitterItemGroup by lazy { 1 }
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