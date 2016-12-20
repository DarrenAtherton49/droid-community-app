package com.darrenatherton.droidcommunity.common.injection.component

import com.darrenatherton.droidcommunity.common.injection.module.ActivityModule
import com.darrenatherton.droidcommunity.common.injection.module.FeedModule
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.feed.FeedFragment
import dagger.Component

@PerScreen
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class, FeedModule::class))
interface FeedComponent : ActivityComponent {

    fun inject(feedFragment: FeedFragment)
}