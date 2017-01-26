package com.darrenatherton.droidcommunity.domain.usecase

import com.darrenatherton.droidcommunity.base.domain.ReactiveUseCaseChild
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.data.reddit.RedditFilterType
import com.darrenatherton.droidcommunity.domain.reddit.RedditListingItem
import com.darrenatherton.droidcommunity.data.reddit.Subreddit
import com.darrenatherton.droidcommunity.domain.reddit.RedditRepository
import rx.Observable
import javax.inject.Inject

@PerScreen
class GetRedditPosts @Inject constructor(private val redditRepository: RedditRepository)
    : ReactiveUseCaseChild<List<RedditListingItem>>() {

    override fun observable(): Observable<List<RedditListingItem>> {

        //todo get subreddit filter list from preferences and delete hard coded stuff below

        //todo merge observables for the 6 subreddits

        val subreddit = Subreddit.ANDROIDDEV
        val filterType = RedditFilterType.HOT

        return redditRepository.getLinksForSubreddit(subreddit, filterType)
    }
}