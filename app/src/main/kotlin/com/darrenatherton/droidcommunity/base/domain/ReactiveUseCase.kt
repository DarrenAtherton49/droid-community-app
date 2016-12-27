package com.darrenatherton.droidcommunity.base.domain

import com.darrenatherton.droidcommunity.common.threading.BackgroundExecutor
import com.darrenatherton.droidcommunity.common.threading.UiExecutor
import rx.Observable
import rx.functions.Action0
import rx.functions.Action1
import rx.subscriptions.Subscriptions

abstract class ReactiveUseCase<ObservableType> (
        private val uiExecutor: UiExecutor,
        private val backgroundExecutor: BackgroundExecutor) {

    private var subscription = Subscriptions.empty()

    protected fun executeUseCase(onNext: Action1<ObservableType>) {
        this.subscription = useCaseObservable()
                .subscribeOn(backgroundExecutor.scheduler)
                .observeOn(uiExecutor.scheduler)
                .subscribe(onNext)
    }

    protected fun executeUseCase(onNext: Action1<ObservableType>,
                                 onError: Action1<Throwable>) {
        this.subscription = useCaseObservable()
                .subscribeOn(backgroundExecutor.scheduler)
                .observeOn(uiExecutor.scheduler)
                .subscribe(onNext, onError)
    }

    protected fun executeUseCase(onNext: Action1<ObservableType>,
                                 onError: Action1<Throwable>,
                                 onCompleted: Action0) {
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