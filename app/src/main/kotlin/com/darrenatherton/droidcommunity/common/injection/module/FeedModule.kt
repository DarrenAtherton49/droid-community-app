package com.darrenatherton.droidcommunity.common.injection.module

import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.feed.FeedListAdapter
import dagger.Module
import dagger.Provides

@Module
class FeedModule {

    @Provides @PerScreen internal fun provideFeedListAdapter() = FeedListAdapter()
}