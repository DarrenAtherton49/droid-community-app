package com.darrenatherton.droidcommunity.feed.reddit.repository

import com.darrenatherton.droidcommunity.feed.entity.FeedItem
import com.darrenatherton.droidcommunity.feed.reddit.data.RedditFilterType
import com.darrenatherton.droidcommunity.feed.reddit.data.Subreddit
import rx.Observable

interface RedditRepository {

    /**
     * Get an [Observable] which will emit a List of Links for a subreddit
     */
    fun getLinksForSubreddit(subreddit: Subreddit, filterType: RedditFilterType): Observable<List<FeedItem.Reddit>>
}