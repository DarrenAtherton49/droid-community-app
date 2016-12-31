package com.darrenatherton.droidcommunity.common.navigation

import android.support.v7.app.AppCompatActivity
import com.darrenatherton.droidcommunity.R
import com.darrenatherton.droidcommunity.common.util.showFragment
import com.darrenatherton.droidcommunity.features.feed.FeedFragment
import com.darrenatherton.droidcommunity.features.feed.entity.FeedViewGroupItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {

    private val mainFragmentContainer = R.id.activity_main_fragment_container

    internal fun showFeedList(activity: AppCompatActivity) {
        activity.showFragment(mainFragmentContainer, ::FeedFragment) {
            // it.setCustomAnimations
            // it.setTransition()
            // etc..
        }
    }

    internal fun showFeedItem(activity: AppCompatActivity, feedViewItem: FeedViewGroupItem) {

    }
}