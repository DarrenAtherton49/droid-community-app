package com.darrenatherton.droidcommunity.features.feed.usecase

import com.darrenatherton.droidcommunity.base.domain.ReactiveUseCase
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.common.threading.IoExecutor
import com.darrenatherton.droidcommunity.common.threading.UiThread
import com.darrenatherton.droidcommunity.reddit.entity.Subscription
import com.darrenatherton.droidcommunity.subscription.repository.SubscriptionRepository
import rx.Observable
import javax.inject.Inject

@PerScreen
class GetSubscriptions @Inject constructor(
        uiThread: UiThread,
        ioExecutor: IoExecutor,
        private val subscriptionRepository: SubscriptionRepository
) : ReactiveUseCase<List<Subscription>>(uiThread, ioExecutor) {

    fun execute(onNext: (List<Subscription>) -> Unit,
                onError: (Throwable) -> Unit,
                onCompleted: () -> Unit): rx.Subscription {
        return super.executeUseCase(onNext, onError, onCompleted)
    }

    override fun useCaseObservable(): Observable<List<Subscription>> {
        return subscriptionRepository.getAllSubscriptions()
    }
}