package com.darrenatherton.droidcommunity.feed

import android.support.annotation.LayoutRes
import com.darrenatherton.droidcommunity.R
import com.darrenatherton.droidcommunity.base.presentation.BaseFragment
import com.darrenatherton.droidcommunity.common.injection.component.DaggerFeedComponent
import javax.inject.Inject

class FeedFragment : BaseFragment<FeedPresenter.View, FeedPresenter>(),
        FeedPresenter.View {

    override val passiveView = this
    @Inject override lateinit var presenter: FeedPresenter
    @LayoutRes override val layoutResId = R.layout.fragment_feed

    //===================================================================================
    // Lifecycle methods and initialization
    //===================================================================================

    //todo val toolbar = findViewById(R.id.toolbar) as Toolbar
    //todo setSupportActionBar(toolbar)

    //===================================================================================
    // Dependency injection
    //===================================================================================

    override fun initInjection() {
        val feedComponent = DaggerFeedComponent.builder()
                .appComponent(appComponent())
                .activityModule(activityModule())
                .build()
        feedComponent.inject(this)
    }

    //===================================================================================
    // View methods
    //===================================================================================


}