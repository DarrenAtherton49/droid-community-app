package com.darrenatherton.droidcommunity.feed

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
import javax.inject.Inject

class FeedFragment : BaseFragment<FeedPresenter.View, FeedPresenter>(),
        FeedPresenter.View, FeedListAdapter.OnItemClickListener {

    interface FeedListNavListener {
        fun showFeedItem(feedItem: FeedItem)
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
        act().supportActionBar?.title = getString(R.string.feed_title)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
    }

    private fun initRecyclerView(rootView: View) {
        feedListAdapter.addOnItemClickListener(this)
        val recyclerView = rootView.findViewById(R.id.fragment_feed_recyclerview) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = feedListAdapter
    }

    override fun onFeedItemClicked(feedItem: FeedItem) {

    }

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
    // View functions
    //===================================================================================

    override fun showFeedItem(feedItem: FeedItem) {
        feedListNavListener?.showFeedItem(feedItem)
    }
}