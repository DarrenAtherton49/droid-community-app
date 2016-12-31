package com.darrenatherton.droidcommunity.reddit.repository

import com.darrenatherton.droidcommunity.reddit.entity.RedditFilterType
import com.darrenatherton.droidcommunity.reddit.entity.RedditListingItem
import com.darrenatherton.droidcommunity.reddit.entity.Subreddit
import com.darrenatherton.droidcommunity.reddit.mapper.RedditDomainMapper
import com.darrenatherton.droidcommunity.reddit.mapper.RedditNetworkResponseMapper
import com.darrenatherton.droidcommunity.reddit.service.RedditService
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditDataRepository @Inject constructor(
        private val redditService: RedditService,
        private val networkResponseMapper: RedditNetworkResponseMapper,
        private val redditDomainMapper: RedditDomainMapper) : RedditRepository {

    override fun getLinksForSubreddit(subreddit: Subreddit, filterType: RedditFilterType): Observable<List<RedditListingItem>> {
        return redditService.getListingForSubreddit(subreddit.urlSuffix, filterType.toString(), 20) //todo remove hard coded limit (20)
                .map { redditListingResponse -> networkResponseMapper.convertLinksResponseToData(redditListingResponse) }
                .doOnNext { /*todo store data item in database*/ }
                .map { dataList -> redditDomainMapper.convertLinksDataToDomain(dataList) }
    }
}