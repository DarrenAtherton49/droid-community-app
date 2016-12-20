package com.darrenatherton.droidcommunity.common.injection.component

import android.content.Context
import android.content.SharedPreferences
import com.darrenatherton.droidcommunity.common.injection.module.AppModule
import com.darrenatherton.droidcommunity.common.navigation.Navigator
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
}