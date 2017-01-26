package com.darrenatherton.droidcommunity.features.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import java.util.*
import javax.inject.Inject

@PerScreen
class MainViewPagerAdapter @Inject constructor(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager) {

    private val fragmentList: MutableList<Fragment> by lazy { ArrayList<Fragment>() }
    private val fragmentTitleList: MutableList<String> by lazy { ArrayList<String>() }

    override fun getItem(position: Int) = fragmentList[position]

    override fun getCount() = fragmentList.size

    override fun getPageTitle(position: Int) = fragmentTitleList[position]

    fun setFragments(vararg fragments: Pair<String, Fragment>) {
        for ((fragmentTitle, fragment) in fragments) {
            fragmentTitleList.add(fragmentTitle)
            fragmentList.add(fragment)
        }
    }
}
