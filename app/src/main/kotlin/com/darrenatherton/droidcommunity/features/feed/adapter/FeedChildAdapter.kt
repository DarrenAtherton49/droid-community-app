package com.darrenatherton.droidcommunity.features.feed.adapter

import android.support.v7.widget.RecyclerView
import com.darrenatherton.droidcommunity.base.presentation.BaseRecyclerViewAdapter

abstract class FeedChildAdapter<ViewHolder : RecyclerView.ViewHolder, ItemType> :
        BaseRecyclerViewAdapter<ViewHolder, ItemType>()