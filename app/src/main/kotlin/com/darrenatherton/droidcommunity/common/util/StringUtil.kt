package com.darrenatherton.droidcommunity.common.util

val emptyString = ""

fun CharSequence?.notNullOrBlank(): Boolean = this != null && !this.isBlank()