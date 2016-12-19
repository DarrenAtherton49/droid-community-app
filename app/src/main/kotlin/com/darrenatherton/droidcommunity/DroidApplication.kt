package com.darrenatherton.droidcommunity

import android.app.Application
import com.darrenatherton.droidcommunity.common.injection.component.AppComponent
import com.darrenatherton.droidcommunity.common.injection.component.DaggerAppComponent
import com.darrenatherton.droidcommunity.common.injection.module.AppModule

class DroidApplication : Application() {

    internal lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initInjection()
    }

    private fun initInjection() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}