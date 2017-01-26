package com.darrenatherton.droidcommunity.features.subscriptiondrawer

import com.darrenatherton.droidcommunity.base.presentation.BasePresenter
import com.darrenatherton.droidcommunity.base.presentation.BaseView
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import javax.inject.Inject

@PerScreen
class SubscriptionDrawerPresenter @Inject constructor() : BasePresenter<SubscriptionDrawerPresenter.View>() {

    //===================================================================================
    // View attach/detach
    //===================================================================================

    override fun onViewAttached() {
        loadSubscriptions()
    }

    override fun onViewDetached() {

    }

    //===================================================================================
    // Domain actions to execute
    //===================================================================================

    private fun loadSubscriptions() {
//        performDomainAction {
//
//        }
    }

    interface View : BaseView {

    }
}