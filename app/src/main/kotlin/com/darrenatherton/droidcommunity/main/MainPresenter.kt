package com.darrenatherton.droidcommunity.main

import com.darrenatherton.droidcommunity.base.presentation.BasePresenter
import com.darrenatherton.droidcommunity.base.presentation.BaseView
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import javax.inject.Inject

@PerScreen
class MainPresenter @Inject constructor() : BasePresenter<MainPresenter.View>() {

    val FEED_TAB = 0
    val CHAT_TAB = 1
    val EVENTS_TAB = 2

    override fun onViewAttached() {

    }

    override fun onViewDetached() {
        performViewAction { detachSubscriptionsMenu() }
    }

    fun onTabSelected(tab: Int) {
        when (tab) {
            FEED_TAB -> {
                performViewAction { setTitleForFeed() }
                performViewAction { enableSubscriptionsMenuSwipe() }
                performViewAction { attachSubscriptionsMenu() }
            }
            CHAT_TAB -> {
                performViewAction { setTitleForChat() }
                performViewAction { disableSubscriptionsMenuSwipe() }
                performViewAction { detachSubscriptionsMenu() }
            }
            EVENTS_TAB -> {
                performViewAction { setTitleForEvents() }
                performViewAction { disableSubscriptionsMenuSwipe() }
                performViewAction { detachSubscriptionsMenu() }
            }
        }
    }

    interface View : BaseView {
        fun setTitleForFeed()
        fun setTitleForChat()
        fun setTitleForEvents()
        fun enableSubscriptionsMenuSwipe()
        fun disableSubscriptionsMenuSwipe()
        fun attachSubscriptionsMenu()
        fun detachSubscriptionsMenu()
    }
}