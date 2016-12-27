package com.darrenatherton.droidcommunity.main

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.darrenatherton.droidcommunity.R
import com.darrenatherton.droidcommunity.base.presentation.BaseActivity
import com.darrenatherton.droidcommunity.common.injection.component.DaggerMainViewComponent
import com.darrenatherton.droidcommunity.common.injection.component.MainViewComponent
import com.darrenatherton.droidcommunity.common.threading.AndroidUiExecutor
import com.darrenatherton.droidcommunity.common.threading.RxIoExecutor
import com.darrenatherton.droidcommunity.feed.reddit.entity.FeedItem
import com.darrenatherton.droidcommunity.feed.reddit.entity.RedditFilterType
import com.darrenatherton.droidcommunity.feed.reddit.entity.RedditLink
import com.darrenatherton.droidcommunity.feed.reddit.entity.Subreddit
import com.darrenatherton.droidcommunity.feed.reddit.mapper.RedditNetworkResponseMapper
import com.darrenatherton.droidcommunity.feed.reddit.repository.RedditDataRepository
import com.darrenatherton.droidcommunity.feed.reddit.repository.RedditRepository
import com.darrenatherton.droidcommunity.feed.reddit.service.RedditService
import com.darrenatherton.droidcommunity.feed.reddit.usecase.GetPosts
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action0
import rx.functions.Action1
import rx.schedulers.Schedulers
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

        val repo: RedditRepository = RedditDataRepository(RedditService.Factory.create(), RedditNetworkResponseMapper())
        repo.getLinksForSubreddit(Subreddit.ANDROIDDEV, RedditFilterType.HOT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onNext -> Log.d("main", "result: " + onNext.forEach { Log.d("main", it.title) })},
                        { onError -> Log.d("main", "error: " + onError)},
                        { Log.d("main", "onComplete")}
                )

        //todo find way to call usecase.execute with the lambda syntax above
        val getPosts: GetPosts = GetPosts(AndroidUiExecutor(), RxIoExecutor(), repo)
        getPosts.execute(object : Action1<List<RedditLink>> {
            override fun call(t: List<RedditLink>?) {

            }
        }, onError = object : Action1<Throwable> {
            override fun call(t: Throwable?) {

            }
        }, onCompleted = object : Action0 {
            override fun call() {

            }
        })
    }

    class Sub : Subscriber<List<RedditLink>>() {

        override fun onCompleted() {

        }

        override fun onNext(t: List<RedditLink>?) {

        }

        override fun onError(e: Throwable?) {

        }
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

    override fun showFeedItem(feedItem: FeedItem) {
        navigator.showFeedItem(this, feedItem)
    }

    /**
     * Finish activity when reaching the last fragment.
     */
    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager;
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack();
        } else {
            finish();
        }
    }
}
