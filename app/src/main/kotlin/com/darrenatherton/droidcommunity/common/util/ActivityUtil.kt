package com.darrenatherton.droidcommunity.common.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

internal inline fun <T : Fragment> AppCompatActivity.showFragment(containerId: Int,
                                                         fragmentClass: () -> T,
                                                         customise: (transaction: FragmentTransaction) -> Unit) {
    val newFragment = fragmentClass()
    val transaction = supportFragmentManager.beginTransaction().apply {
        customise(this)
        val currentFragment = supportFragmentManager.findFragmentById(containerId)
        if (currentFragment == null) {
            add(containerId, newFragment)
        } else {
            replace(containerId, newFragment)
        }
    }
    transaction.commit()
}