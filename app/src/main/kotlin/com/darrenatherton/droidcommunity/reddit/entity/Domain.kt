package com.darrenatherton.droidcommunity.reddit.entity

sealed class Subscription(val title: String) {
    class Reddit(title: String, val subreddit: Subreddit) : Subscription(title)
    class Twitter(title: String) : Subscription(title)
    //class Dribbble() : Subscription() etc...
}

data class RedditListingItem(val title: String,
                             val subreddit: String,
                             val author: String,
                             val submitted: String,
                             val numComments: String)