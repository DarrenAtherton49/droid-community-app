package com.darrenatherton.droidcommunity.data.subscription.service

import com.darrenatherton.droidcommunity.data.subscription.SubscriptionData
import com.darrenatherton.droidcommunity.data.subscription.SubscriptionsData
import rx.Observable

interface SubscriptionService {

    fun getAllSubscriptions(): Observable<SubscriptionsData>

    fun getUserSubscriptions(): Observable<List<SubscriptionData>> //todo same as above, also add user id as param
}