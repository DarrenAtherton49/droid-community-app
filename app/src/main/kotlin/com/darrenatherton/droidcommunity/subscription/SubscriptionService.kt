package com.darrenatherton.droidcommunity.subscription

import com.darrenatherton.droidcommunity.reddit.entity.Subscription
import rx.Observable

interface SubscriptionService {

    fun getAllSubscriptions(): Observable<List<Subscription>> //todo return data class which is equivalent to subscription stored in kotlin

    fun getUserSubscriptions(): Observable<List<Subscription>> //todo same as above, also add user id as param
}