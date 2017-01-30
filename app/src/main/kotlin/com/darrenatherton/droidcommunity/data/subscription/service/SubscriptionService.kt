package com.darrenatherton.droidcommunity.data.subscription.service

import com.darrenatherton.droidcommunity.data.subscription.SubscriptionData
import rx.Observable

interface SubscriptionService {

    fun getAllSubscriptions(): Observable<List<SubscriptionData>>

    fun getUserSubscriptions(): Observable<List<SubscriptionData>> //todo same as above, also add user id as param
}