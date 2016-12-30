package com.darrenatherton.droidcommunity.feed.shared.entity

sealed class FeedItem(val title: String) {
    class Reddit(title: String,
                 val subreddit: String,
                 val author: String,
                 val submitted: String,
                 val numComments: String) : FeedItem(title)

    class Twitter(title: String) : FeedItem(title)
}