package com.m.samplelibb.rx

import co.starfish.rx.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulersFacade: SchedulerProvider {
    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}