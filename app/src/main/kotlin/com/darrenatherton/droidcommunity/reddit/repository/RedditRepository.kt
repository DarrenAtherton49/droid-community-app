package com.darrenatherton.droidcommunity.reddit.repository

import com.darrenatherton.droidcommunity.reddit.entity.RedditFilterType
import com.darrenatherton.droidcommunity.reddit.entity.RedditListingItem
import com.darrenatherton.droidcommunity.reddit.entity.Subreddit
import rx.Observable

interface RedditRepository {

    /**
     * Get an [Observable] which will emit a List of Links for a subreddit
     */
    fun getLinksForSubreddit(subreddit: Subreddit, filterType: RedditFilterType): Observable<List<RedditListingItem>>
}