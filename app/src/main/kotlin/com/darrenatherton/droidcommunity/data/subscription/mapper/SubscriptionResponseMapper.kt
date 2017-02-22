package com.darrenatherton.droidcommunity.data.subscription.mapper

import com.darrenatherton.droidcommunity.data.subscription.SubscriptionData
import com.google.firebase.database.DataSnapshot

/**
 * Functions used to transform data from Firebase responses to valid data layer objects.
 */

internal fun convertSubscriptionsResponseToData(dataSnapshot: DataSnapshot): List<SubscriptionData> {
    return dataSnapshot.children.map {
        val subscription = it.getValue(SubscriptionData::class.java)
        SubscriptionData(it.key, subscription.readable, subscription.type, subscription.order)
    }
}