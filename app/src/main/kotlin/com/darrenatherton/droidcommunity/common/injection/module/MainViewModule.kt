package com.darrenatherton.droidcommunity.common.injection.module

import android.support.v7.app.AppCompatActivity
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.main.MainViewPagerAdapter
import dagger.Module
import dagger.Provides

@Module
class MainViewModule(private val activity: AppCompatActivity) {

    @Provides @PerScreen internal fun provideMainViewPagerAdapter(): MainViewPagerAdapter {
        return MainViewPagerAdapter(activity.supportFragmentManager)
    }
}