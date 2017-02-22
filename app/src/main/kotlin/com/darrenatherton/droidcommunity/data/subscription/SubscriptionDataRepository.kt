package com.darrenatherton.droidcommunity.data.subscription

import com.darrenatherton.droidcommunity.data.subscription.mapper.convertSubscriptionsDataToDomain
import com.darrenatherton.droidcommunity.data.subscription.service.SubscriptionService
import com.darrenatherton.droidcommunity.domain.subscription.Subscription
import com.darrenatherton.droidcommunity.domain.subscription.SubscriptionRepository
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubscriptionDataRepository @Inject constructor(
        private val subscriptionService: SubscriptionService
) : SubscriptionRepository {

    override fun getAllSubscriptions(): Observable<List<Subscription>> {
        return subscriptionService.getAllSubscriptions()
                .map { convertSubscriptionsDataToDomain(it) }
    }

    override fun getUserSubscriptions(): Observable<List<Subscription>> {
        //todo return subscriptionService
        //    .map { firebase data class to domain subscription (i.e. 'Subscription' }
        throw UnsupportedOperationException("not implemented")
    }
}