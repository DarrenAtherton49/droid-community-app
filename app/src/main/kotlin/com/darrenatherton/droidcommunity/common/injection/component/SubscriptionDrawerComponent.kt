package com.darrenatherton.droidcommunity.common.injection.component

import com.darrenatherton.droidcommunity.common.injection.module.SubscriptionDrawerModule
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.features.subscriptiondrawer.SubscriptionDrawerView
import dagger.Component

@PerScreen
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(SubscriptionDrawerModule::class))
interface SubscriptionDrawerComponent {

    fun inject(subscriptionDrawerView: SubscriptionDrawerView)
}