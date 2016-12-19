package com.darrenatherton.droidcommunity.base.presentation

abstract class BasePresenter<View: BaseView> {

    protected lateinit var view: View

    protected var isViewAttached = false
        private set

    fun viewAttached(view: View) {
        this.view = view
        isViewAttached = true
        onViewAttached()
    }

    fun viewDetached() {
        isViewAttached = false
        onViewDetached()
    }

    /*
     * Implement this method to perform initialisation, subscribe to any reactive
     * streams/observables etc. when the view is attached.
     */
    protected abstract fun onViewAttached()

    /*
     * Implement this method to unsubscribe from any reactive streams/observables when the
     * view is detached.
     */
    protected abstract fun onViewDetached()
}