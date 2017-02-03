package com.darrenatherton.droidcommunity.base.presentation

import android.support.v7.widget.RecyclerView
import java.util.*

/**
 * This class should be subclassed by RecyclerView adapters whose items also have (nested)
 * RecyclerView adapters. Subclasses of this adapter act as the View component in an MVP pattern,
 * and this class handles the attaching/detaching of the presenter in response to attaching/detaching
 * from its RecyclerView.
 */
abstract class BaseRecyclerWithChildAdapter<ViewHolder : RecyclerView.ViewHolder,
        View : BaseView,
        out Presenter : BasePresenter<View>,
        ItemType>(protected var items: List<ItemType> = Collections.emptyList())
    : RecyclerView.Adapter<ViewHolder>() {

    protected abstract val passiveView: View
    protected abstract val presenter: Presenter

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        presenter.viewAttached(passiveView)
        //todo check whether this is the best place to pass the presenter
    }

    override fun onViewRecycled(holder: ViewHolder) {
        presenter.viewDetached()
        super.onViewRecycled(holder)
    }

    // Sometimes, if animations are running on the itemView's children, the RecyclerView won't be
    // able to recycle the view. We should still unbind the presenter.
    override fun onFailedToRecycleView(holder: ViewHolder): Boolean {
        presenter.viewDetached()
        return super.onFailedToRecycleView(holder)
    }

    override fun getItemCount() = items.size

    protected fun getItem(position: Int) = items[position]
}