package com.darrenatherton.droidcommunity.data.reddit

import com.darrenatherton.droidcommunity.data.reddit.RedditFilterType
import com.darrenatherton.droidcommunity.data.reddit.RedditResponseMapper
import com.darrenatherton.droidcommunity.data.reddit.Subreddit
import com.darrenatherton.droidcommunity.domain.reddit.RedditListingItem
import com.darrenatherton.droidcommunity.domain.reddit.RedditDomainMapper
import com.darrenatherton.droidcommunity.domain.reddit.RedditRepository
import com.darrenatherton.droidcommunity.data.reddit.service.RedditService
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditDataRepository @Inject constructor(
        private val redditService: RedditService,
        private val networkResponseMapper: RedditResponseMapper,
        private val redditDomainMapper: RedditDomainMapper) : RedditRepository {

    override fun getLinksForSubreddit(subreddit: Subreddit, filterType: RedditFilterType): Observable<List<RedditListingItem>> {
        return redditService.getListingForSubreddit(subreddit.urlSuffix, filterType.toString(), 20) //todo remove hard coded limit (20)
                .map { redditListingResponse -> networkResponseMapper.convertLinksResponseToData(redditListingResponse) }
                .doOnNext { /*todo store data item in database*/ }
                .map { dataList -> redditDomainMapper.convertLinksDataToDomain(dataList) }
    }
}