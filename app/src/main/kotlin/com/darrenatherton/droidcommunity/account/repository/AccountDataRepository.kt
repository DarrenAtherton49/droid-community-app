package com.darrenatherton.droidcommunity.account.repository

import com.darrenatherton.droidcommunity.reddit.entity.Subreddit
import com.darrenatherton.droidcommunity.reddit.entity.Subscription
import rx.Observable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountDataRepository @Inject constructor(
        //todo inject shared preferences etc
) : AccountRepository {

    override fun getSubscriptions(): Observable<List<Subscription>> {
        //todo implement from data layer/storage and convert through data > domain mapper
        val subscriptions: MutableList<Subscription> = ArrayList()
        subscriptions.add(Subscription.Reddit(Subreddit.ANDROIDDEV.label, Subreddit.ANDROIDDEV))
        subscriptions.add(Subscription.Reddit(Subreddit.ANDROID.label, Subreddit.ANDROID))
        return Observable.just(subscriptions)
    }
}