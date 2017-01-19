package com.darrenatherton.droidcommunity.main

import com.darrenatherton.droidcommunity.base.presentation.BasePresenter
import com.darrenatherton.droidcommunity.base.presentation.BaseView
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import javax.inject.Inject

@PerScreen
class MainPresenter @Inject constructor() : BasePresenter<MainPresenter.View>() {

    private val FEED = 0
    private val CHAT = 1
    private val EVENTS = 2

    override fun onViewAttached() {

    }

    override fun onViewDetached() {

    }

    fun onTabSelected(tab: Int) {
        when (tab) {
            FEED -> performViewAction { setTitleForFeed() }
            CHAT -> performViewAction { setTitleForChat() }
            EVENTS -> performViewAction { setTitleForEvents() }
        }
    }

    interface View : BaseView {
        fun setTitleForFeed()
        fun setTitleForChat()
        fun setTitleForEvents()
    }
}