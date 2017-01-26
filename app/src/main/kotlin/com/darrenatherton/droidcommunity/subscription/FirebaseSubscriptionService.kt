package com.darrenatherton.droidcommunity.subscription

import com.darrenatherton.droidcommunity.reddit.entity.Subscription
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseSubscriptionService @Inject constructor(
        //todo inject firebase
) : SubscriptionService {

    override fun getAllSubscriptions(): Observable<List<Subscription>> {
        //todo call firebase
        throw UnsupportedOperationException("not implemented")
    }

    override fun getUserSubscriptions(): Observable<List<Subscription>> {
        //todo call firebase
        throw UnsupportedOperationException("not implemented")
    }
}