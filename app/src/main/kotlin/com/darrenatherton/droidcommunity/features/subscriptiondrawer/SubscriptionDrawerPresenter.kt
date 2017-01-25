package com.darrenatherton.droidcommunity.features.subscriptiondrawer

import com.darrenatherton.droidcommunity.base.presentation.BasePresenter
import com.darrenatherton.droidcommunity.base.presentation.BaseView
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import javax.inject.Inject

@PerScreen
class SubscriptionDrawerPresenter @Inject constructor() : BasePresenter<SubscriptionDrawerPresenter.View>() {

    override fun onViewDetached() {

    }

    override fun onViewAttached() {

    }



    interface View : BaseView {

    }
}