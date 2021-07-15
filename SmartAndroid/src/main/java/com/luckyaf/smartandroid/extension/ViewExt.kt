package com.luckyaf.smartandroid.extension

import android.view.View

/**
 * 类描述：
 * @author Created by luckyAF on 2021/4/21
 *
 */

/*** 点击相关 ****/
/**
 *  延时触发  即 一段时间内点击无反应
 */
private var <T : View> T.triggerDelay: Long
    get() = if (getTag(1123461123) != null) getTag(1123461123) as Long else 300
    set(value) {
        setTag(1123461123, value)
    }

/**
 *  上次点击事件
 */
private var <T : View> T.triggerLastTime: Long
    get() = if (getTag(1123460103) != null) getTag(1123460103) as Long else 0
    set(value) {
        setTag(1123460103, value)
    }

private fun <T : View> T.delayOver(): Boolean {
    var flag = false
    val currentClickTime = System.currentTimeMillis()
    if (currentClickTime - triggerLastTime >= triggerDelay) {
        triggerLastTime = currentClickTime
        flag = true
    }
    return flag
}

/***
 * 带延迟过滤的点击事件View扩展
 * @param time Long 延迟时间，默认600毫秒
 * @param block: (T) -> Unit 函数
 * @return Unit
 */
@Suppress("UNCHECKED_CAST")
fun <T : View> T.clickWithTrigger(time: Long = 600, block: (T) -> Unit) {
    triggerDelay = time
    setOnClickListener {
        if (delayOver()) {
            block(it as T)
        }
    }
}



/*** 可见性相关 ****/
fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

var View.isGone: Boolean
    get() = visibility == View.GONE
    set(value) {
        visibility = if (value) View.GONE else View.VISIBLE
    }

var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.INVISIBLE
    }


var View.isInvisible: Boolean
    get() = visibility == View.INVISIBLE
    set(value) {
        visibility = if (value) View.INVISIBLE else View.VISIBLE
    }
