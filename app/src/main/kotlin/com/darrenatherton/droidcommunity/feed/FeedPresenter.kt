package com.darrenatherton.droidcommunity.feed

import com.darrenatherton.droidcommunity.base.presentation.BasePresenter
import com.darrenatherton.droidcommunity.base.presentation.BaseView
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import javax.inject.Inject

@PerScreen
class FeedPresenter @Inject constructor() : BasePresenter<FeedPresenter.View>() {

    override fun onViewAttached() {

    }

    override fun onViewDetached() {

    }

    interface View : BaseView {

    }
}