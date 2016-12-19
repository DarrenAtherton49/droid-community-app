package com.darrenatherton.droidcommunity.common.threading

import rx.Scheduler

interface UiExecutor {
    val scheduler: Scheduler
}