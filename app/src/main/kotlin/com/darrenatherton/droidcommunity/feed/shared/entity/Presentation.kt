package com.darrenatherton.droidcommunity.feed.shared.entity

sealed class FeedViewItem(val title: String, val viewType: Int) {

    class Reddit(title: String,
                 val subreddit: String,
                 val author: String,
                 val submitted: String,
                 val numComments: String) : FeedViewItem(title, reddit)

    class Twitter(title: String) : FeedViewItem(title, twitter)

    companion object Type {
        val reddit by lazy { 0 }
        val twitter by lazy { 1 }
    }
}