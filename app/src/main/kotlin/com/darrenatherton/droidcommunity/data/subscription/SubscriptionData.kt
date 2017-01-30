package com.darrenatherton.droidcommunity.data.subscription

import java.util.*

data class SubscriptionsData(val subscriptions: Map<String, SubscriptionData> = HashMap())

data class SubscriptionData(val readable: String = "", val type: String = "", val order: Int = 0)