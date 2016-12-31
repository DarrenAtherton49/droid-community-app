package com.darrenatherton.droidcommunity.main

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.darrenatherton.droidcommunity.R
import com.darrenatherton.droidcommunity.base.presentation.BaseActivity
import com.darrenatherton.droidcommunity.common.injection.component.DaggerMainViewComponent
import com.darrenatherton.droidcommunity.common.injection.component.MainViewComponent
import com.darrenatherton.droidcommunity.features.feed.entity.FeedViewGroupItem
import javax.inject.Inject

class MainActivity : BaseActivity<MainPresenter.View, MainPresenter>(),
        MainPresenter.View, MainNavigation, AHBottomNavigation.OnTabSelectedListener {

    private lateinit var mainViewComponent: MainViewComponent
    override val passiveView = this
    @Inject override lateinit var presenter: MainPresenter
    @LayoutRes override val layoutResId = R.layout.activity_main

    private val FEED = 0
    private val CHAT = 1
    private val EVENTS = 2

    //===================================================================================
    // Lifecycle functions and initialization
    //===================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        initBottomNav()
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


    private fun initBottomNav() {
        val bottomNav = findViewById(R.id.bottom_nav) as AHBottomNavigation
        val tabs = listOf(
                AHBottomNavigationItem(R.string.feed_tab, R.drawable.ic_profile_tab, R.color.bottom_nav),
                AHBottomNavigationItem(R.string.chat_tab, R.drawable.ic_profile_tab, R.color.bottom_nav),
                AHBottomNavigationItem(R.string.events_tab, R.drawable.ic_profile_tab, R.color.bottom_nav)
        )
        bottomNav.addItems(tabs)

        bottomNav.titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW
        bottomNav.isForceTint = true
        bottomNav.isColored = true // colored navigation with circular reveal effect
        onTabSelected(FEED, false)
        bottomNav.setOnTabSelectedListener(this)
    }

    override fun onTabSelected(position: Int, wasSelected: Boolean): Boolean {
        if (!wasSelected) {
            when (position) {
                FEED -> presenter.onFeedButtonClicked()
                CHAT -> presenter.onChatButtonClicked()
                EVENTS -> presenter.onEventsButtonClicked()
            }
        }
        return !wasSelected
    }

    //===================================================================================
    // Dependency injection
    //===================================================================================

    override fun initInjection() {
        mainViewComponent = DaggerMainViewComponent.builder()
                .appComponent(appComponent())
                .activityModule(activityModule())
                .build()
        mainViewComponent.inject(this)
    }

    //===================================================================================
    // View functions
    //===================================================================================

    override fun showFeed() {
        navigator.showFeedList(this)
    }

    override fun showChat() {

    }

    override fun showEvents() {

    }

    //===================================================================================
    // Navigation functions from fragments
    //===================================================================================

    override fun showFeedItem(feedViewGroupItem: FeedViewGroupItem) {
        navigator.showFeedItem(this, feedViewGroupItem)
    }

    /**
     * Finish activity when reaching the last fragment.
     */
    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}
