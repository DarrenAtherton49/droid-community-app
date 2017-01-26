package com.darrenatherton.droidcommunity.features.feed

import android.util.Log
import com.darrenatherton.droidcommunity.base.presentation.BasePresenter
import com.darrenatherton.droidcommunity.base.presentation.BaseView
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.features.feed.entity.SubscriptionViewItem
import com.darrenatherton.droidcommunity.features.feed.mapper.RedditFeedPresentationMapper
import com.darrenatherton.droidcommunity.domain.usecase.GetSubscriptions
import javax.inject.Inject

@PerScreen
class FeedPresenter @Inject constructor(private val getSubscriptions: GetSubscriptions,
                                        private val presentationMapper: RedditFeedPresentationMapper)
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

        performDomainAction {
            getSubscriptions.execute(
                    onNext = {
                        val list = presentationMapper.convertSubscriptionsDomainToView(it)
                        list.forEach { Log.d("darren", it.title) }
                        performViewAction { showSubscriptions(list) }
                    },
                    onError = { Log.d("darren", it.message ) },
                    onCompleted = { Log.d("darren", "onCompleted") }
            )
        }
    }

    //===================================================================================
    // Actions forwarded from view
    //===================================================================================

    internal fun onFeedItemClicked(subscriptionViewItem: SubscriptionViewItem) {
        performViewAction { showSubscriptionDetail(subscriptionViewItem) }
    }

    //===================================================================================
    // View definition
    //===================================================================================

    interface View : BaseView {
        fun showSubscriptions(items: List<SubscriptionViewItem>)
        fun showSubscriptionDetail(subscriptionViewItem: SubscriptionViewItem)
    }
}