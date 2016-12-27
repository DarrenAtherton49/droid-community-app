package com.darrenatherton.droidcommunity.feed

import android.util.Log
import com.darrenatherton.droidcommunity.base.presentation.BasePresenter
import com.darrenatherton.droidcommunity.base.presentation.BaseView
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.feed.entity.FeedViewItem
import com.darrenatherton.droidcommunity.feed.mapper.PresentationMapper
import com.darrenatherton.droidcommunity.feed.reddit.usecase.GetPosts
import javax.inject.Inject

@PerScreen
class FeedPresenter @Inject constructor(private val getPosts: GetPosts) : BasePresenter<FeedPresenter.View>() {

    //===================================================================================
    // View attach/detach
    //===================================================================================

    override fun onViewAttached() {
        loadPosts()
    }

    override fun onViewDetached() {
        getPosts.unsubscribe()
    }

    //===================================================================================
    // Domain actions to execute
    //===================================================================================

    private fun loadPosts() {
        getPosts.execute(
                onNext = {
                    val list = PresentationMapper().convertFeedItemsDomainToPresentation(it)
                    list.forEach { Log.d("darren", it.title) }
                },
                onError = { Log.d("darren", it.message )},
                onCompleted = { Log.d("darren", "onCompleted") }
        )
    }

    //===================================================================================
    // Actions forwarded from view
    //===================================================================================

    internal fun onFeedItemClicked(feedViewItem: FeedViewItem) {
        performViewAction { showFeedItem(feedViewItem) }
    }

    //===================================================================================
    // View definition
    //===================================================================================

    interface View : BaseView {
        fun showFeedItem(feedViewItem: FeedViewItem)
    }
}