package com.darrenatherton.droidcommunity.common.injection.component

import android.content.Context
import android.content.SharedPreferences
import com.darrenatherton.droidcommunity.common.injection.module.AppModule
import com.darrenatherton.droidcommunity.common.navigation.Navigator
import com.darrenatherton.droidcommunity.common.threading.ComputationExecutor
import com.darrenatherton.droidcommunity.common.threading.IoExecutor
import com.darrenatherton.droidcommunity.common.threading.UiThread
import com.darrenatherton.droidcommunity.feed.reddit.repository.RedditRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Dagger component which lasts for the entire app lifecycle
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun context(): Context
    fun sharedPreferences(): SharedPreferences
    fun navigator(): Navigator
    fun uiThread(): UiThread
    fun ioExecutor(): IoExecutor
    fun computationExecutor(): ComputationExecutor
    fun redditRepository(): RedditRepository
}