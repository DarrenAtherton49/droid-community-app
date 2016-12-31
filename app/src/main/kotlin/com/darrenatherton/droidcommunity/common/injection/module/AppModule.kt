package com.darrenatherton.droidcommunity.common.injection.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.darrenatherton.droidcommunity.account.repository.AccountDataRepository
import com.darrenatherton.droidcommunity.account.repository.AccountRepository
import com.darrenatherton.droidcommunity.common.navigation.Navigator
import com.darrenatherton.droidcommunity.common.threading.*
import com.darrenatherton.droidcommunity.reddit.mapper.RedditDomainMapper
import com.darrenatherton.droidcommunity.reddit.mapper.RedditNetworkResponseMapper
import com.darrenatherton.droidcommunity.reddit.repository.RedditDataRepository
import com.darrenatherton.droidcommunity.reddit.repository.RedditRepository
import com.darrenatherton.droidcommunity.reddit.service.RedditService
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

    @Provides @Singleton internal fun provideRedditService() = RedditService.Factory.create()

    @Provides @Singleton internal fun provideRedditRepository(redditService: RedditService,
                                                              networkResponseMapper: RedditNetworkResponseMapper,
                                                              redditDomainMapper: RedditDomainMapper): RedditRepository {
        return RedditDataRepository(redditService, networkResponseMapper, redditDomainMapper)
    }

    @Provides @Singleton internal fun provideAccountRepository(): AccountRepository {
        return AccountDataRepository()
    }
}
