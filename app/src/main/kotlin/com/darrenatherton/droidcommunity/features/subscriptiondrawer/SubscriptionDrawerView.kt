package com.darrenatherton.droidcommunity.features.subscriptiondrawer

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.darrenatherton.droidcommunity.DroidApplication
import com.darrenatherton.droidcommunity.common.injection.component.DaggerSubscriptionDrawerComponent
import com.darrenatherton.droidcommunity.common.injection.component.SubscriptionDrawerComponent
import javax.inject.Inject

class SubscriptionDrawerView : RecyclerView, SubscriptionDrawerPresenter.View {

    private lateinit var subscriptionDrawerComponent: SubscriptionDrawerComponent
    @Inject lateinit var presenter: SubscriptionDrawerPresenter

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs)
    }

    //===================================================================================
    // Lifecycle functions and initialization
    //===================================================================================

    fun init(context: Context, attrs: AttributeSet? = null) {
        initInjection()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.viewAttached(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.viewDetached()
    }

    //===================================================================================
    // Dependency injection
    //===================================================================================

    private fun initInjection() {
        subscriptionDrawerComponent = DaggerSubscriptionDrawerComponent.builder()
                .appComponent((context.applicationContext as DroidApplication).appComponent)
                .build()
        subscriptionDrawerComponent.inject(this)
    }

    //===================================================================================
    // View functions
    //===================================================================================
}