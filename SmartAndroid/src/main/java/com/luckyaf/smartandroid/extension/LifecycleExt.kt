package com.luckyaf.smartandroid.extension

import androidx.lifecycle.*

/**
 * 类描述：
 * @author Created by luckyAF on 2021/10/22
 *
 */


fun Lifecycle.OnStop(block: () -> Unit):Lifecycle {
    addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop() {
            block.invoke()
        }
    })
    return this
}

fun Lifecycle.OnDestroy(block: () -> Unit):Lifecycle  {
    addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            block.invoke()
        }
    })
    return this
}