package com.darrenatherton.droidcommunity.domain.reddit

import com.darrenatherton.droidcommunity.data.reddit.RedditFilterType
import com.darrenatherton.droidcommunity.domain.reddit.RedditListingItem
import com.darrenatherton.droidcommunity.data.reddit.Subreddit
import rx.Observable

interface RedditRepository {

    /**
     * Get an [Observable] which will emit a List of Links for a subreddit
     */
    fun getLinksForSubreddit(subreddit: Subreddit, filterType: RedditFilterType): Observable<List<RedditListingItem>>
}