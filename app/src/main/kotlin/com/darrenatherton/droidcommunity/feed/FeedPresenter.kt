package com.darrenatherton.droidcommunity.feed

import com.darrenatherton.droidcommunity.base.presentation.BasePresenter
import com.darrenatherton.droidcommunity.base.presentation.BaseView
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.feed.reddit.entity.FeedViewItem
import javax.inject.Inject

@PerScreen
class FeedPresenter @Inject constructor() : BasePresenter<FeedPresenter.View>() {

    override fun onViewAttached() {

    }

    override fun onViewDetached() {

    }

    internal fun onFeedItemClicked(feedViewItem: FeedViewItem) {
        performViewAction { showFeedItem(feedViewItem) }
    }

    interface View : BaseView {
        fun showFeedItem(feedViewItem: FeedViewItem)
    }
}