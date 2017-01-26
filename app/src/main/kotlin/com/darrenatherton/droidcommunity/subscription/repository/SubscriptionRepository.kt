package com.darrenatherton.droidcommunity.subscription.repository

import com.darrenatherton.droidcommunity.reddit.entity.Subscription
import rx.Observable

interface SubscriptionRepository {

    // add one subscription

    // remove one subscription

    fun getAllSubscriptions(): Observable<List<Subscription>>

    fun getUserSubscriptions(): Observable<List<Subscription>>
}