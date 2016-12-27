package com.darrenatherton.droidcommunity.common.injection.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.darrenatherton.droidcommunity.common.navigation.Navigator
import com.darrenatherton.droidcommunity.common.threading.*
import com.darrenatherton.droidcommunity.feed.reddit.mapper.RedditNetworkResponseMapper
import com.darrenatherton.droidcommunity.feed.reddit.repository.RedditDataRepository
import com.darrenatherton.droidcommunity.feed.reddit.repository.RedditRepository
import com.darrenatherton.droidcommunity.feed.reddit.service.RedditService
import dagger.Module
import dagger.Provides
import javax.inject.Named
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

    @Provides @Singleton internal fun provideUiThread(androidUiThread: AndroidUiExecutor): UiExecutor {
        return androidUiThread
    }

    @Provides @Singleton @Named("ioExecutor") internal fun provideIoExecutor(
            rxIoExecutor: RxIoExecutor): BackgroundExecutor {
        return rxIoExecutor
    }

    @Provides @Singleton @Named("computationExecutor") internal fun provideComputationExecutor(
            rxComputationThread: RxComputationExecutor): BackgroundExecutor {
        return rxComputationThread
    }

    @Provides @Singleton internal fun provideRedditService() = RedditService.Factory.create()

    @Provides @Singleton internal fun provideRedditRepository(redditService: RedditService,
                                                              redditResponseMapper: RedditNetworkResponseMapper): RedditRepository {
        return RedditDataRepository(redditService, redditResponseMapper)
    }
}
