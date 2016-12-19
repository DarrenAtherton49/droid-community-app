package com.darrenatherton.droidcommunity.common.injection.component

import com.darrenatherton.droidcommunity.common.injection.module.ActivityModule
import com.darrenatherton.droidcommunity.common.injection.module.MainViewModule
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.main.MainActivity
import dagger.Component

@PerScreen
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class, MainViewModule::class))
interface MainViewComponent : ActivityComponent {

    fun inject(mainActivity: MainActivity)
}