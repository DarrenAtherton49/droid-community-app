package com.darrenatherton.droidcommunity.features.feed.usecase

import com.darrenatherton.droidcommunity.account.repository.AccountRepository
import com.darrenatherton.droidcommunity.base.domain.ReactiveUseCase
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.common.threading.IoExecutor
import com.darrenatherton.droidcommunity.common.threading.UiThread
import com.darrenatherton.droidcommunity.reddit.entity.Subscription
import rx.Observable
import javax.inject.Inject

@PerScreen
class GetFeedItemGroups @Inject constructor(
        uiExecutor: UiThread,
        ioExecutor: IoExecutor,
        private val accountRepository: AccountRepository
) : ReactiveUseCase<List<Subscription>>(uiExecutor, ioExecutor) {

    fun execute(onNext: (List<Subscription>) -> Unit,
                onError: (Throwable) -> Unit,
                onCompleted: () -> Unit) {
        super.executeUseCase(onNext, onError, onCompleted)
    }

    override fun useCaseObservable(): Observable<List<Subscription>> {
        return accountRepository.getSubscriptions()
    }
}