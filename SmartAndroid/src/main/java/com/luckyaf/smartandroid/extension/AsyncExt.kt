package com.luckyaf.smartandroid.extension

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 类描述：
 * @author Created by luckyAF on 2021/12/6
 *
 */
fun postDelayed(delayMillis: Long, task: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(task, delayMillis)
}

fun LifecycleOwner.runOnIOScope(block: () -> Unit) {
    lifecycleScope.launch {
        withContext(Dispatchers.IO) {
            block()
        }
    }
}
