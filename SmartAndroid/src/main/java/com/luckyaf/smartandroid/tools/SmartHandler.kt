package com.luckyaf.smartandroid.tools

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * 类描述：
 * @author Created by luckyAF on 2021/10/22
 *
 */

class SmartHandler(lifecycleOwner: LifecycleOwner, callback: Callback): Handler(Looper.getMainLooper(),callback),
    LifecycleObserver {
    private val mLifecycleOwner: LifecycleOwner = lifecycleOwner

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        removeCallbacksAndMessages(null)
        mLifecycleOwner.lifecycle.removeObserver(this)
    }
}