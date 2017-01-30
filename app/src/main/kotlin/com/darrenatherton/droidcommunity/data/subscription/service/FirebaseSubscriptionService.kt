package com.darrenatherton.droidcommunity.data.subscription.service

import com.darrenatherton.droidcommunity.data.subscription.SubscriptionData
import com.darrenatherton.droidcommunity.data.subscription.mapper.SubscriptionResponseMapper
import com.google.firebase.database.FirebaseDatabase
import com.kelvinapps.rxfirebase.RxFirebaseDatabase
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseSubscriptionService @Inject constructor(
        val firebase: FirebaseDatabase,
        val responseMapper: SubscriptionResponseMapper
) : SubscriptionService {

    override fun getAllSubscriptions(): Observable<List<SubscriptionData>> {
        return RxFirebaseDatabase.observeSingleValueEvent(firebase.getReference("subscriptions"))
                .map { responseMapper.convertSubscriptionsResponseToData(it) }
    }

    override fun getUserSubscriptions(): Observable<List<SubscriptionData>> {
        //todo call firebase - add user id as param. Then concatenate userId into the firebase
        // database reference string and observe it.
        // e.g.: /users/${userId}
        throw UnsupportedOperationException("not implemented")
    }
}