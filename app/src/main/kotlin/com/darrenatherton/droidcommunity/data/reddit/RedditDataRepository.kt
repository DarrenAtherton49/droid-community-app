package com.darrenatherton.droidcommunity.data.reddit

import com.darrenatherton.droidcommunity.data.reddit.mapper.convertLinksDataToDomain
import com.darrenatherton.droidcommunity.data.reddit.mapper.convertLinksResponseToData
import com.darrenatherton.droidcommunity.data.reddit.service.RedditService
import com.darrenatherton.droidcommunity.domain.reddit.RedditListingItem
import com.darrenatherton.droidcommunity.domain.reddit.RedditRepository
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditDataRepository @Inject constructor(
        private val redditService: RedditService) : RedditRepository {

    override fun getLinksForSubreddit(subreddit: Subreddit, filterType: RedditFilterType): Observable<List<RedditListingItem>> {
        return redditService.getListingForSubreddit(subreddit.urlSuffix, filterType.toString(), 20) //todo remove hard coded limit (20)
                .map { redditListingResponse -> convertLinksResponseToData(redditListingResponse) }
                .doOnNext { /*todo store data item in database*/ }
                .map { dataList -> convertLinksDataToDomain(dataList) }
    }
}