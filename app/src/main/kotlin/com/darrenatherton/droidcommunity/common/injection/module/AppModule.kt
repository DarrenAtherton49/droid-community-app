package com.darrenatherton.droidcommunity.common.injection.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.darrenatherton.droidcommunity.common.navigation.Navigator
import com.darrenatherton.droidcommunity.common.threading.*
import com.darrenatherton.droidcommunity.reddit.mapper.RedditDomainMapper
import com.darrenatherton.droidcommunity.reddit.mapper.RedditNetworkResponseMapper
import com.darrenatherton.droidcommunity.reddit.repository.RedditDataRepository
import com.darrenatherton.droidcommunity.reddit.repository.RedditRepository
import com.darrenatherton.droidcommunity.reddit.service.RedditService
import com.darrenatherton.droidcommunity.subscription.FirebaseSubscriptionService
import com.darrenatherton.droidcommunity.subscription.SubscriptionService
import com.darrenatherton.droidcommunity.subscription.repository.SubscriptionDataRepository
import com.darrenatherton.droidcommunity.subscription.repository.SubscriptionRepository
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

    @Provides @Singleton internal fun provideRedditRepository(redditService: RedditService,
                                                              networkResponseMapper: RedditNetworkResponseMapper,
                                                              redditDomainMapper: RedditDomainMapper): RedditRepository {
        return RedditDataRepository(redditService, networkResponseMapper, redditDomainMapper)
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
        return FirebaseSubscriptionService()
    }
}
