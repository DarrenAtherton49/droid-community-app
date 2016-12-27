package com.darrenatherton.droidcommunity

import android.app.Application
import com.darrenatherton.droidcommunity.common.injection.component.AppComponent
import com.darrenatherton.droidcommunity.common.injection.component.DaggerAppComponent
import com.darrenatherton.droidcommunity.common.injection.module.AppModule
import com.squareup.leakcanary.LeakCanary

class DroidApplication : Application() {

    internal lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)

        initInjection()
    }

    private fun initInjection() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}