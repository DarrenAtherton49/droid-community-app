package com.darrenatherton.droidcommunity.features.subscriptiondrawer

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

class SubscriptionDrawerView : RecyclerView {

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs)
    }

    fun init(context: Context, attrs: AttributeSet? = null) {

    }
}