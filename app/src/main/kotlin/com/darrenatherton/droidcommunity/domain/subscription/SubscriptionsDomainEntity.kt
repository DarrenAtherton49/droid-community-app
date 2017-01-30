package com.darrenatherton.droidcommunity.domain.subscription

sealed class Subscription(val key: String, val title: String, val order: Int) {
    class Reddit(key: String, title: String, order: Int) : Subscription(key, title, order)
    class Twitter(key: String, title: String, order: Int) : Subscription(key, title, order)
    //class Dribbble() : Subscription() etc...
}