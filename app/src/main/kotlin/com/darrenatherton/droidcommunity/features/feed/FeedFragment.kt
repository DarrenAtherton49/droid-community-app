package com.darrenatherton.droidcommunity.features.feed

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.darrenatherton.droidcommunity.R
import com.darrenatherton.droidcommunity.base.presentation.BaseFragment
import com.darrenatherton.droidcommunity.common.injection.component.DaggerFeedComponent
import com.darrenatherton.droidcommunity.common.injection.module.FeedModule
import com.darrenatherton.droidcommunity.features.feed.adapter.FeedListAdapter
import com.darrenatherton.droidcommunity.features.feed.entity.SubscriptionViewItem
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject

class FeedFragment : BaseFragment<FeedPresenter.View, FeedPresenter>(), FeedPresenter.View,
        FeedListAdapter.OnItemClickListener {

    interface FeedListNavListener {
        fun showSubscription(subscriptionViewItem: SubscriptionViewItem)
    }

    override val passiveView = this
    @Inject override lateinit var presenter: FeedPresenter
    @LayoutRes override val layoutResId = R.layout.fragment_feed

    @Inject lateinit var feedListAdapter: FeedListAdapter
    private var feedListNavListener: FeedListNavListener? = null

    //===================================================================================
    // Lifecycle functions and initialization
    //===================================================================================

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FeedListNavListener) {
            feedListNavListener = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    override fun onDetach() {
        super.onDetach()
        this.feedListNavListener = null
    }

    private fun initRecyclerView() {
        feedListAdapter.addOnItemClickListener(this)
        val recyclerView = feedRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = feedListAdapter
    }

    override fun onSubscriptionItemClicked(subscriptionViewItem: SubscriptionViewItem) {

    }

    //===================================================================================
    // Dependency injection
    //===================================================================================

    override fun initInjection() {
        val feedComponent = DaggerFeedComponent.builder()
                .appComponent(appComponent())
                .activityModule(activityModule())
                .feedModule(FeedModule())
                .build()
        feedComponent.inject(this)
    }

    //===================================================================================
    // View functions
    //===================================================================================

    override fun showSubscriptions(items: List<SubscriptionViewItem>) {
        feedListAdapter.replaceData(items)
    }

    override fun showSubscriptionDetail(subscriptionViewItem: SubscriptionViewItem) {
        feedListNavListener?.showSubscription(subscriptionViewItem)
    }
}