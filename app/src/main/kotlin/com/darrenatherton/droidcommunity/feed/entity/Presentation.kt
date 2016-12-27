package com.darrenatherton.droidcommunity.feed.entity

sealed class FeedViewItem(val title: String) {
    class Reddit(title: String) : FeedViewItem(title)
}