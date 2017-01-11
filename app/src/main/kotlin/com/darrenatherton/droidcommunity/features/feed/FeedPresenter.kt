package com.darrenatherton.droidcommunity.features.feed

import android.util.Log
import com.darrenatherton.droidcommunity.base.presentation.BasePresenter
import com.darrenatherton.droidcommunity.base.presentation.BaseView
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.features.feed.entity.FeedViewGroupItem
import com.darrenatherton.droidcommunity.features.feed.mapper.RedditFeedPresentationMapper
import com.darrenatherton.droidcommunity.features.feed.usecase.GetFeedItemGroups
import javax.inject.Inject

@PerScreen
class FeedPresenter @Inject constructor(private val getFeedItemGroups: GetFeedItemGroups,
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
            getFeedItemGroups.execute(
                    onNext = {
                        val list = presentationMapper.convertSubscriptionsToFeedItemGroups(it)
                        list.forEach { Log.d("darren", it.title) }
                        performViewAction { showFeedItemGroups(list) }
                    },
                    onError = { Log.d("darren", it.message ) },
                    onCompleted = { Log.d("darren", "onCompleted") }
            )
        }
    }

    //===================================================================================
    // Actions forwarded from view
    //===================================================================================

    internal fun onFeedItemClicked(feedViewGroupItem: FeedViewGroupItem) {
        performViewAction { showFeedItemDetail(feedViewGroupItem) }
    }

    //===================================================================================
    // View definition
    //===================================================================================

    interface View : BaseView {
        fun showFeedItemGroups(groupItems: List<FeedViewGroupItem>)
        fun showFeedItemDetail(feedViewGroupItem: FeedViewGroupItem)
    }
}