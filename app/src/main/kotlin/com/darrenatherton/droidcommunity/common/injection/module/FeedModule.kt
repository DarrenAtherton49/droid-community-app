package com.darrenatherton.droidcommunity.common.injection.module

import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.features.feed.FeedItemPresenter
import com.darrenatherton.droidcommunity.features.feed.adapter.FeedListAdapter
import dagger.Module
import dagger.Provides

@Module
class FeedModule {

    @Provides @PerScreen internal fun provideFeedListAdapter(
            feedItemPresenter: FeedItemPresenter): FeedListAdapter {
        return FeedListAdapter(feedItemPresenter)
    }
}