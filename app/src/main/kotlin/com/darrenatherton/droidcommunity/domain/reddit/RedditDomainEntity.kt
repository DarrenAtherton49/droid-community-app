package com.darrenatherton.droidcommunity.domain.reddit

data class RedditListingItem(val title: String,
                             val subreddit: String,
                             val author: String,
                             val submitted: String,
                             val numComments: String)