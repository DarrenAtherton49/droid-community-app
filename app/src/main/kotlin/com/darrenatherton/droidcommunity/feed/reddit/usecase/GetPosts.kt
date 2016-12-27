package com.darrenatherton.droidcommunity.feed.reddit.usecase

import com.darrenatherton.droidcommunity.base.domain.ReactiveUseCase
import com.darrenatherton.droidcommunity.common.threading.BackgroundExecutor
import com.darrenatherton.droidcommunity.common.threading.UiExecutor
import com.darrenatherton.droidcommunity.feed.reddit.entity.RedditFeedItem
import com.darrenatherton.droidcommunity.feed.reddit.entity.RedditFilterType
import com.darrenatherton.droidcommunity.feed.reddit.entity.Subreddit
import com.darrenatherton.droidcommunity.feed.reddit.repository.RedditRepository
import rx.Observable
import javax.inject.Inject
import javax.inject.Named

class GetPosts @Inject constructor(
        uiExecutor: UiExecutor,
        @Named("ioExecutor") backgroundExecutor: BackgroundExecutor,
        private val redditRepository: RedditRepository
) : ReactiveUseCase<List<RedditFeedItem>>(uiExecutor, backgroundExecutor) {

    fun execute(onNext: (List<RedditFeedItem>) -> Unit,
                onError: (Throwable) -> Unit,
                onCompleted: () -> Unit) {
        super.executeUseCase(onNext, onError, onCompleted)
    }

    override fun useCaseObservable(): Observable<List<RedditFeedItem>> {

        //todo get subreddit filter list from preferences and delete hard coded stuff below

        //todo merge observables for the 6 subreddits

        val subreddit = Subreddit.ANDROIDDEV
        val filterType = RedditFilterType.HOT

        return redditRepository.getLinksForSubreddit(subreddit, filterType)
    }
}