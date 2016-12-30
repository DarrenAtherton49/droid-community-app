package com.darrenatherton.droidcommunity.feed.reddit.usecase

import com.darrenatherton.droidcommunity.base.domain.ReactiveUseCaseChild
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.feed.reddit.entity.RedditFilterType
import com.darrenatherton.droidcommunity.feed.reddit.entity.Subreddit
import com.darrenatherton.droidcommunity.feed.reddit.repository.RedditRepository
import com.darrenatherton.droidcommunity.feed.shared.entity.FeedItem
import rx.Observable
import javax.inject.Inject

@PerScreen
class GetRedditPosts @Inject constructor(private val redditRepository: RedditRepository)
    : ReactiveUseCaseChild<List<FeedItem.Reddit>>() {

    override fun observable(): Observable<List<FeedItem.Reddit>> {

        //todo get subreddit filter list from preferences and delete hard coded stuff below

        //todo merge observables for the 6 subreddits

        val subreddit = Subreddit.ANDROIDDEV
        val filterType = RedditFilterType.HOT

        return redditRepository.getLinksForSubreddit(subreddit, filterType)
    }
}