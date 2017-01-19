package com.darrenatherton.droidcommunity.main

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.darrenatherton.droidcommunity.R
import com.darrenatherton.droidcommunity.base.presentation.BaseActivity
import com.darrenatherton.droidcommunity.common.injection.component.DaggerMainViewComponent
import com.darrenatherton.droidcommunity.common.injection.component.MainViewComponent
import com.darrenatherton.droidcommunity.common.injection.module.MainViewModule
import com.darrenatherton.droidcommunity.features.feed.FeedFragment
import com.darrenatherton.droidcommunity.features.feed.entity.SubscriptionViewItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_appbar_tabs.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainPresenter.View, MainPresenter>(),
        MainPresenter.View, MainNavigation, ViewPager.OnPageChangeListener {

    private lateinit var mainViewComponent: MainViewComponent
    override val passiveView = this
    @Inject override lateinit var presenter: MainPresenter
    @LayoutRes override val layoutResId = R.layout.activity_main
    @Inject internal lateinit var viewPagerAdapter: MainViewPagerAdapter

    //===================================================================================
    // Lifecycle functions and initialization
    //===================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.title = getString(R.string.feed_title)

        initTabs()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initTabs() {
        viewPagerAdapter.setFragments(
                getString(R.string.feed_title) to FeedFragment(),
                getString(R.string.chat_title) to FeedFragment(),
                getString(R.string.events_title) to FeedFragment()
        )
        viewPagerMain.adapter = viewPagerAdapter
        viewPagerMain.addOnPageChangeListener(this)
        tablayoutMain.setupWithViewPager(viewPagerMain)
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        presenter.onTabSelected(position)
    }

    //===================================================================================
    // Dependency injection
    //===================================================================================

    override fun initInjection() {
        mainViewComponent = DaggerMainViewComponent.builder()
                .appComponent(appComponent())
                .activityModule(activityModule())
                .mainViewModule(MainViewModule(this))
                .build()
        mainViewComponent.inject(this)
    }

    //===================================================================================
    // View functions
    //===================================================================================

    override fun setTitleForFeed() {
        supportActionBar?.title = getString(R.string.feed_title)
    }

    override fun setTitleForChat() {
        supportActionBar?.title = getString(R.string.chat_title)
    }

    override fun setTitleForEvents() {
        supportActionBar?.title = getString(R.string.events_title)
    }

    //===================================================================================
    // Navigation functions from fragments
    //===================================================================================

    override fun showSubscription(subscriptionViewItem: SubscriptionViewItem) {
        //navigator.showSubscription(this, subscriptionViewItem)
    }
}
