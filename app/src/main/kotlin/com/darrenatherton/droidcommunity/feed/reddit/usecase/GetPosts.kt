package com.darrenatherton.droidcommunity.feed.reddit.usecase

import com.darrenatherton.droidcommunity.base.domain.ReactiveUseCase
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.common.threading.IoExecutor
import com.darrenatherton.droidcommunity.common.threading.UiThread
import com.darrenatherton.droidcommunity.feed.entity.FeedItem
import com.darrenatherton.droidcommunity.feed.reddit.data.RedditFilterType
import com.darrenatherton.droidcommunity.feed.reddit.data.Subreddit
import com.darrenatherton.droidcommunity.feed.reddit.repository.RedditRepository
import rx.Observable
import javax.inject.Inject

@PerScreen
class GetPosts @Inject constructor(
        uiExecutor: UiThread,
        ioExecutor: IoExecutor,
        private val redditRepository: RedditRepository
) : ReactiveUseCase<List<FeedItem.Reddit>>(uiExecutor, ioExecutor) {

    fun execute(onNext: (List<FeedItem.Reddit>) -> Unit,
                onError: (Throwable) -> Unit,
                onCompleted: () -> Unit) {
        super.executeUseCase(onNext, onError, onCompleted)
    }

    override fun useCaseObservable(): Observable<List<FeedItem.Reddit>> {

        //todo get subreddit filter list from preferences and delete hard coded stuff below

        //todo merge observables for the 6 subreddits

        val subreddit = Subreddit.ANDROIDDEV
        val filterType = RedditFilterType.HOT

        return redditRepository.getLinksForSubreddit(subreddit, filterType)
    }
}