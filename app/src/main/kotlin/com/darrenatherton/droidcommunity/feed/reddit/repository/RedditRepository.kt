package com.darrenatherton.droidcommunity.feed.reddit.repository

import com.darrenatherton.droidcommunity.feed.reddit.entity.RedditFeedItem
import com.darrenatherton.droidcommunity.feed.reddit.entity.RedditFilterType
import com.darrenatherton.droidcommunity.feed.reddit.entity.Subreddit
import rx.Observable

interface RedditRepository {

    /**
     * Get an [Observable] which will emit a List of Links for a subreddit
     */
    fun getLinksForSubreddit(subreddit: Subreddit, filterType: RedditFilterType): Observable<List<RedditFeedItem>>
}