package com.darrenatherton.droidcommunity.common.injection.component

import android.app.Activity
import com.darrenatherton.droidcommunity.common.injection.module.ActivityModule
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import dagger.Component

@PerScreen
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun activity(): Activity
}
