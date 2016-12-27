package com.darrenatherton.droidcommunity.feed.reddit.repository

import com.darrenatherton.droidcommunity.feed.reddit.entity.RedditFilterType
import com.darrenatherton.droidcommunity.feed.reddit.entity.RedditLink
import com.darrenatherton.droidcommunity.feed.reddit.entity.Subreddit
import com.darrenatherton.droidcommunity.feed.reddit.mapper.RedditNetworkResponseMapper
import com.darrenatherton.droidcommunity.feed.reddit.service.RedditService
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditDataRepository @Inject constructor(
        private val redditService: RedditService,
        private val redditResponseMapper: RedditNetworkResponseMapper) : RedditRepository {

    override fun getLinksForSubreddit(subreddit: Subreddit, filterType: RedditFilterType): Observable<List<RedditLink>> {
        return redditService.getListingForSubreddit(subreddit.urlSuffix, filterType.toString())
                .map {
                    redditListingResponse ->
                        redditResponseMapper.transformListingResponseToLinkCollection(redditListingResponse)
                }
    }
}