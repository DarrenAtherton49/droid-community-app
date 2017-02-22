package com.darrenatherton.droidcommunity.base.presentation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.darrenatherton.droidcommunity.DroidApplication
import com.darrenatherton.droidcommunity.common.injection.module.ActivityModule

abstract class BaseFragment<View: BaseView, Presenter : BasePresenter<View>> : Fragment() {

    protected abstract val passiveView: View
    protected abstract val presenter: dagger.Lazy<Presenter>
    protected abstract val layoutResId: Int

    protected val navigator by lazy { appComponent().navigator() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initInjection()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): android.view.View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(layoutResId, container, false)
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.get().viewAttached(passiveView)
    }

    override fun onDestroyView() {
        presenter.get().viewDetached()
        super.onDestroyView()
    }

    protected fun act() = activity as AppCompatActivity

    protected fun appComponent() = (act().application as DroidApplication).appComponent

    protected fun activityModule() = ActivityModule(act())

    protected abstract fun initInjection()
}