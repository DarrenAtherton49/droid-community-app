package com.darrenatherton.droidcommunity.feed.reddit.usecase

import com.darrenatherton.droidcommunity.base.domain.ReactiveUseCase
import com.darrenatherton.droidcommunity.common.threading.BackgroundExecutor
import com.darrenatherton.droidcommunity.common.threading.UiExecutor
import com.darrenatherton.droidcommunity.feed.reddit.entity.RedditFilterType
import com.darrenatherton.droidcommunity.feed.reddit.entity.RedditLink
import com.darrenatherton.droidcommunity.feed.reddit.entity.Subreddit
import com.darrenatherton.droidcommunity.feed.reddit.repository.RedditRepository
import rx.Observable
import rx.functions.Action0
import rx.functions.Action1
import javax.inject.Inject
import javax.inject.Named

class GetPosts @Inject constructor(
        uiExecutor: UiExecutor,
        @Named("ioExecutor") backgroundExecutor: BackgroundExecutor,
        private val redditRepository: RedditRepository
) : ReactiveUseCase<List<RedditLink>>(uiExecutor, backgroundExecutor) {

    fun execute(onNext: Action1<List<RedditLink>>,
                onError: Action1<Throwable>,
                onCompleted: Action0) {
        super.executeUseCase(onNext, onError, onCompleted)
    }

    override fun useCaseObservable(): Observable<List<RedditLink>> {

        //todo get subreddit filter list from preferences and delete hard coded stuff below

        //todo merge observables for the 6 subreddits

        val subreddit = Subreddit.ANDROIDDEV
        val filterType = RedditFilterType.HOT

        return redditRepository.getLinksForSubreddit(subreddit, filterType)
    }
}