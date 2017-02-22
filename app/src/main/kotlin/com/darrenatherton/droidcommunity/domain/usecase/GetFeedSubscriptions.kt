package com.darrenatherton.droidcommunity.domain.usecase

import android.util.Log
import com.darrenatherton.droidcommunity.base.domain.ReactiveUseCase
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.common.threading.IoExecutor
import com.darrenatherton.droidcommunity.common.threading.UiThread
import com.darrenatherton.droidcommunity.domain.subscription.Subscription
import com.darrenatherton.droidcommunity.domain.subscription.SubscriptionRepository
import rx.Observable
import java.util.*
import javax.inject.Inject


@PerScreen
class GetFeedSubscriptions @Inject constructor(
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
        subscriptions()
                .map { subscriptions ->
                    val mutable: MutableList<Observable<String>> = ArrayList()
                    subscriptions.forEach { subscription ->
                        mutable.add(getItemsForSub(subscription))
                    }
                    mutable
                }
                .map {
                    Observable.zip(it) {
                        it.toList()
                    }
                }
                .subscribe {
                    it.forEach {
                        it.forEach {
                            Log.d("darren", "" + it.toString())
                        }
                    }
                }

        return Observable.just(Collections.emptyList())
    }

    private fun getItemsForSub(sub: Subscription): Observable<String> = Observable.just("")

    private fun subscriptions(): Observable<List<Subscription>> = subscriptionRepository.getAllSubscriptions()
}