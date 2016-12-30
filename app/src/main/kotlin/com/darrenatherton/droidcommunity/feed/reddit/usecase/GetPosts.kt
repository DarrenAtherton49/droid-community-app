package com.darrenatherton.droidcommunity.feed.reddit.usecase

import com.darrenatherton.droidcommunity.base.domain.ReactiveUseCase
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.common.threading.IoExecutor
import com.darrenatherton.droidcommunity.common.threading.UiThread
import com.darrenatherton.droidcommunity.feed.shared.entity.FeedItem
import rx.Observable
import javax.inject.Inject

@PerScreen
class GetPosts @Inject constructor(
        uiExecutor: UiThread,
        ioExecutor: IoExecutor,
        private val getRedditPosts: GetRedditPosts
) : ReactiveUseCase<List<FeedItem>>(uiExecutor, ioExecutor) {

    fun execute(onNext: (List<FeedItem>) -> Unit,
                onError: (Throwable) -> Unit,
                onCompleted: () -> Unit) {
        super.executeUseCase(onNext, onError, onCompleted)
    }

    override fun useCaseObservable(): Observable<List<FeedItem>> {
        return Observable.zip(
                getRedditPosts.observable(),
                getRedditPosts.observable(), //todo change to twitter
                { redditPosts, twitterPosts ->
                    redditPosts + twitterPosts
                }
        )
    }
}