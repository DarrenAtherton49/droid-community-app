package com.darrenatherton.droidcommunity.feed.entity

sealed class FeedItem(val title: String) {
    class Reddit(title: String) : FeedItem(title)
}