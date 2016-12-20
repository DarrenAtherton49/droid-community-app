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
        performViewAction { showFeed() }
    }

    internal fun onChatButtonClicked() {
        performViewAction { showChat() }
    }

    internal fun onEventsButtonClicked() {
        performViewAction { showEvents() }
    }

    interface View : BaseView {
        fun showFeed()
        fun showChat()
        fun showEvents()
    }
}