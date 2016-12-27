package com.darrenatherton.droidcommunity.base.domain

import com.darrenatherton.droidcommunity.common.threading.BackgroundExecutor
import com.darrenatherton.droidcommunity.common.threading.UiThread
import rx.Observable
import rx.subscriptions.Subscriptions

abstract class ReactiveUseCase<ObservableType> (
        private val uiExecutor: UiThread,
        private val backgroundExecutor: BackgroundExecutor) {

    private var subscription = Subscriptions.empty()

    protected fun executeUseCase(onNext: (ObservableType) -> Unit = {},
                                 onError: (Throwable) -> Unit = {},
                                 onCompleted: () -> Unit = {}) {
        this.subscription = useCaseObservable()
                .subscribeOn(backgroundExecutor.scheduler)
                .observeOn(uiExecutor.scheduler)
                .subscribe(onNext, onError, onCompleted)
    }

    protected abstract fun useCaseObservable(): Observable<ObservableType>

    fun unsubscribe() {
        if (!subscription.isUnsubscribed) {
            subscription.unsubscribe()
        }
    }
}