package com.darrenatherton.droidcommunity.account.repository
import com.darrenatherton.droidcommunity.reddit.entity.Subscription
import rx.Observable

interface AccountRepository {

    fun getSubscriptions(): Observable<List<Subscription>>
}