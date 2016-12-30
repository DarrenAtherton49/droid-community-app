package com.darrenatherton.droidcommunity.feed.shared

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.darrenatherton.droidcommunity.R
import com.darrenatherton.droidcommunity.base.presentation.BaseFragment
import com.darrenatherton.droidcommunity.common.injection.component.DaggerFeedComponent
import com.darrenatherton.droidcommunity.common.injection.module.FeedModule
import com.darrenatherton.droidcommunity.feed.shared.entity.FeedViewItem
import javax.inject.Inject

class FeedFragment : BaseFragment<FeedPresenter.View, FeedPresenter>(), FeedPresenter.View,
        FeedListAdapter.OnItemClickListener {

    interface FeedListNavListener {
        fun showFeedItem(feedViewItem: FeedViewItem)
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
        if (context is FeedFragment.FeedListNavListener) {
            feedListNavListener = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        act().supportActionBar?.title = getString(R.string.feed_title)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
    }

    override fun onDetach() {
        super.onDetach()
        this.feedListNavListener = null
    }

    private fun initRecyclerView(rootView: android.view.View) {
        feedListAdapter.addOnItemClickListener(this)
        val recyclerView = rootView.findViewById(R.id.fragment_feed_recyclerview) as RecyclerView
        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % 3 == 0) 2 else 1
            }
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = feedListAdapter
    }

    override fun onFeedItemClicked(feedViewItem: FeedViewItem) {

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

    override fun showFeedItemsList(items: List<FeedViewItem>) {
        feedListAdapter.replaceData(items)
    }

    override fun showFeedItemDetail(feedViewItem: FeedViewItem) {
        feedListNavListener?.showFeedItem(feedViewItem)
    }
}