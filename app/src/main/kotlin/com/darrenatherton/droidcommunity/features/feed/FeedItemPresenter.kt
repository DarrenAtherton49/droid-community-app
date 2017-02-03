package com.darrenatherton.droidcommunity.features.feed

import com.darrenatherton.droidcommunity.base.presentation.BasePresenter
import com.darrenatherton.droidcommunity.base.presentation.BaseView
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.domain.usecase.GetSubscriptions
import com.darrenatherton.droidcommunity.features.feed.mapper.RedditFeedPresentationMapper
import javax.inject.Inject

@PerScreen
class FeedItemPresenter @Inject constructor(private val getSubscriptions: GetSubscriptions,
                                            private val presentationMapper: RedditFeedPresentationMapper)
    : BasePresenter<FeedItemPresenter.View>() {

    //===================================================================================
    // View attach/detach
    //===================================================================================

    override fun onViewAttached() {

    }

    override fun onViewDetached() {

    }

    //===================================================================================
    // Domain actions to execute
    //===================================================================================


    //===================================================================================
    // Actions forwarded from view
    //===================================================================================


    //===================================================================================
    // View definition
    //===================================================================================

    interface View : BaseView {

    }
}