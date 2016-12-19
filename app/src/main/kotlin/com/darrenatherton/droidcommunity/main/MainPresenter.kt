package com.darrenatherton.droidcommunity.main

import com.darrenatherton.droidcommunity.base.presentation.BasePresenter
import com.darrenatherton.droidcommunity.base.presentation.BaseView
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import javax.inject.Inject

@PerScreen
class MainPresenter @Inject constructor() : BasePresenter<MainPresenter.View>() {

    override fun onViewAttached() {

    }

    override fun onViewDetached() {

    }

    internal fun onFeedButtonClicked() {
        view.showFeed()
    }

    internal fun onChatButtonClicked() {
        view.showChat()
    }

    internal fun onEventsButtonClicked() {
        view.showEvents()
    }

    internal fun onProfileButtonClicked() {
        view.showProfile()
    }

    interface View : BaseView {
        fun showFeed()
        fun showChat()
        fun showEvents()
        fun showProfile()
    }
}