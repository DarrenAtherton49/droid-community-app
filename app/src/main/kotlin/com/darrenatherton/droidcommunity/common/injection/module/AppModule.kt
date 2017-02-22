package com.darrenatherton.droidcommunity.common.injection.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.darrenatherton.droidcommunity.common.navigation.Navigator
import com.darrenatherton.droidcommunity.common.threading.*
import com.darrenatherton.droidcommunity.data.reddit.RedditDataRepository
import com.darrenatherton.droidcommunity.data.reddit.service.RedditService
import com.darrenatherton.droidcommunity.data.subscription.SubscriptionDataRepository
import com.darrenatherton.droidcommunity.data.subscription.service.FirebaseSubscriptionService
import com.darrenatherton.droidcommunity.data.subscription.service.SubscriptionService
import com.darrenatherton.droidcommunity.domain.reddit.RedditRepository
import com.darrenatherton.droidcommunity.domain.subscription.SubscriptionRepository
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module that provides objects which last for the entire app lifecycle
 */
@Module
class AppModule(private val application: Application) {

    @Provides @Singleton internal fun provideApplicationContext(): Context {
        return application
    }

    @Provides @Singleton internal fun provideSharedPrefs(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Provides @Singleton internal fun provideNavigator() = Navigator()

    //===================================================================================
    // Threading
    //===================================================================================

    @Provides @Singleton internal fun provideUiThread(androidUiThread: AndroidUiThread): UiThread {
        return androidUiThread
    }

    @Provides @Singleton internal fun provideIoExecutor(
            rxIoExecutor: RxIoExecutor): IoExecutor {
        return rxIoExecutor
    }

    @Provides @Singleton internal fun provideComputationExecutor(
            rxComputationExecutor: RxComputationExecutor): ComputationExecutor {
        return rxComputationExecutor
    }

    //===================================================================================
    // Repositories
    //===================================================================================

    @Provides @Singleton internal fun provideRedditRepository(redditService: RedditService): RedditRepository {
        return RedditDataRepository(redditService)
    }

    @Provides @Singleton internal fun provideSubscriptionRepository(
            subscriptionService: SubscriptionService): SubscriptionRepository {
        return SubscriptionDataRepository(subscriptionService)
    }

    //===================================================================================
    // Services
    //===================================================================================

    @Provides @Singleton internal fun provideRedditService() = RedditService.Factory.create()

    @Provides @Singleton internal fun provideSubscriptionService(): SubscriptionService {
        return FirebaseSubscriptionService(FirebaseDatabase.getInstance())
    }
}
