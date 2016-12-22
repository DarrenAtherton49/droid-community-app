package com.darrenatherton.droidcommunity.common.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

internal inline fun <T : Fragment> AppCompatActivity.showFragment(containerId: Int,
                                                         fragmentClass: () -> T,
                                                         clearBackStack: Boolean = false,
                                                         customise: (transaction: FragmentTransaction) -> Unit) {
    val newFragment = fragmentClass()
    if (clearBackStack) {
        clearBackStack(supportFragmentManager)
    }
    val transaction = supportFragmentManager.beginTransaction().apply {
        customise(this)
        addToBackStack(null)
        replace(containerId, newFragment)
    }
    transaction.commit()
}

private fun clearBackStack(fragmentManager: FragmentManager) {
    if (fragmentManager.backStackEntryCount > 0) {
        val first = fragmentManager.getBackStackEntryAt(0);
        fragmentManager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}