package com.darrenatherton.droidcommunity.data.subscription.service

import com.darrenatherton.droidcommunity.domain.reddit.Subscription
import com.google.firebase.database.FirebaseDatabase
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseSubscriptionService @Inject constructor(
        val firebaseDatabase: FirebaseDatabase
) : SubscriptionService {

    override fun getAllSubscriptions(): Observable<List<Subscription>> {
        //todo call firebase
        throw UnsupportedOperationException("not implemented")
    }

    override fun getUserSubscriptions(): Observable<List<Subscription>> {
        //todo call firebase - add user id as param. Then concatenate userId into the firebase
        // database reference string and observe it.
        // e.g.: /users/${userId}
        throw UnsupportedOperationException("not implemented")
    }
}