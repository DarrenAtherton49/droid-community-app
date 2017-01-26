package com.darrenatherton.droidcommunity.domain.subscription

import com.darrenatherton.droidcommunity.domain.reddit.Subscription
import rx.Observable

interface SubscriptionRepository {

    // add one subscription

    // remove one subscription

    fun getAllSubscriptions(): Observable<List<Subscription>>

    fun getUserSubscriptions(): Observable<List<Subscription>>
}