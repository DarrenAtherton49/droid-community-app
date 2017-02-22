package com.darrenatherton.droidcommunity.features.feed

import android.util.Log
import com.darrenatherton.droidcommunity.base.presentation.BasePresenter
import com.darrenatherton.droidcommunity.base.presentation.BaseView
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.domain.usecase.GetFeedSubscriptions
import javax.inject.Inject

@PerScreen
class FeedPresenter @Inject constructor(private val getSubscriptions: GetFeedSubscriptions)
    : BasePresenter<FeedPresenter.View>() {

    //===================================================================================
    // View attach/detach
    //===================================================================================

    override fun onViewAttached() {
        loadFeed()
    }

    override fun onViewDetached() {

    }

    //===================================================================================
    // Domain actions to execute
    //===================================================================================

    private fun loadFeed() {
        //todo show/hide progress/retry etc

        getSubscriptions.execute(
                onNext = {
                    it.forEach { Log.d("darren", it.title) }
                },
                onError = { Log.d("darren", "ERROR: " + it.message ) },
                onCompleted = { Log.d("darren", "onCompleted") }
        )
    }

    //===================================================================================
    // Actions forwarded from view
    //===================================================================================

    internal fun onFeedItemClicked(subscriptionFeedItem: SubscriptionFeedItem) {
        performViewAction { showSubscriptionDetail(subscriptionFeedItem) }
    }

    //===================================================================================
    // View definition
    //===================================================================================

    interface View : BaseView {
        fun showSubscriptions(items: List<SubscriptionViewItem>)
        fun showSubscriptionDetail(subscriptionFeedItem: SubscriptionFeedItem)
    }
}