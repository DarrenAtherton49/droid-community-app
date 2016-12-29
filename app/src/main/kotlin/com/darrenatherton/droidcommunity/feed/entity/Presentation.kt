package com.darrenatherton.droidcommunity.feed.entity

sealed class FeedViewItem(val title: String, val viewType: Int) {
    class Reddit(title: String, val subreddit: String) : FeedViewItem(title, reddit)

    companion object Type {
        val reddit by lazy { 0 }
    }
}