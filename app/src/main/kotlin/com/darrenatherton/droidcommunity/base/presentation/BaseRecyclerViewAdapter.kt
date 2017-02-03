package com.darrenatherton.droidcommunity.base.presentation

import android.support.v7.widget.RecyclerView
import java.util.*

abstract class BaseRecyclerViewAdapter<ViewHolder : RecyclerView.ViewHolder, ItemType>(
        protected var items: List<ItemType> = Collections.emptyList()) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount() = items.size

    protected fun getItem(position: Int) = items[position]

    protected fun replaceData(newItems: List<ItemType>) {
        items = newItems
        notifyDataSetChanged() //todo use DiffUtils
    }
}