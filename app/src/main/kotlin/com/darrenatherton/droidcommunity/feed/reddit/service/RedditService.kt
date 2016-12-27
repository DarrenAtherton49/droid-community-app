package com.darrenatherton.droidcommunity.feed.reddit.service

import com.darrenatherton.droidcommunity.BuildConfig
import com.darrenatherton.droidcommunity.feed.reddit.data.RedditListing
import com.darrenatherton.droidcommunity.feed.reddit.data.RedditObject
import com.darrenatherton.droidcommunity.feed.reddit.data.RedditResponse
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable
import javax.inject.Singleton

@Singleton
interface RedditService {

    @GET("/r/{subreddit}/{filter_type}.json")
    fun getListingForSubreddit(
            @Path("subreddit") subreddit: String,
            @Path("filter_type") filterType: String
    ): Observable<RedditResponse<RedditListing>>

    object Factory {
        fun create(): RedditService {

            val gson = GsonBuilder()
                    .registerTypeAdapter(RedditObject::class.java, RedditObjectDeserializer())
                    .create()

            val logLevel = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            val logging = HttpLoggingInterceptor()
            logging.level = logLevel

            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(RedditHeaderInterceptor())
                    .addInterceptor(logging)
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.reddit.com/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()

            return retrofit.create(RedditService::class.java)
        }
    }
}