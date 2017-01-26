package com.darrenatherton.droidcommunity.data.reddit.service

import okhttp3.Interceptor
import okhttp3.Response

class RedditHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithHeaders = originalRequest.newBuilder()
                .header("User-Agent", "android:com.darrenatherton.droidmagazine:v1 (by /u/athertonD)")
                .build()
        return chain.proceed(requestWithHeaders)
    }
}