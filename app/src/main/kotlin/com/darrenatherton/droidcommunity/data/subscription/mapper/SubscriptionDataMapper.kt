package com.darrenatherton.droidcommunity.data.subscription.mapper

import com.darrenatherton.droidcommunity.data.subscription.SubscriptionData
import com.darrenatherton.droidcommunity.domain.subscription.Subscription

/**
 * Functions used to transform Subscription objects from data layer to domain layer.
 */

private val REDDIT = "reddit"
private val TWITTER = "twitter"

internal fun convertSubscriptionsDataToDomain(subscriptionsData: List<SubscriptionData>): List<Subscription> {
    return subscriptionsData.map { convertSubscriptionDataToDomain(it) }
}

private fun convertSubscriptionDataToDomain(subscriptionData: SubscriptionData) = with(subscriptionData) {
    when (type) {
        REDDIT -> Subscription.Reddit(key, readable, order)
        TWITTER -> Subscription.Twitter(key, readable, order)
        else -> Subscription.Reddit(key, readable, order)
    }
}