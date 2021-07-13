package com.luckyaf.smartandroid.action;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

/**
 * 类描述： Handler 处理
 *
 * @author Created by luckyAF on 2021/6/29
 */
@SuppressWarnings("unused")
public interface HandlerAction {

    Handler HANDLER = new Handler(Looper.getMainLooper());

    /**
     * 获取 Handler
     * @return 返回主线程的handler
     */
    default Handler getHandler() {
        return HANDLER;
    }

    /**
     * 立即执行
     * @param  r runnable
     * @return 是否成功放入队列
     */
    default boolean post(Runnable r) {
        return postDelayed(0,r);
    }

    /**
     * 延迟一段时间执行
     * 在指定的时间执行
     * 将runnable放后面 方便lambda或者kotlin
     * @param  delayMillis 延迟时间
     * @param  r runnable
     * @return 是否成功放入队列
     */
    default boolean postDelayed(long delayMillis,Runnable r) {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        return postAtTime(SystemClock.uptimeMillis() + delayMillis,r );
    }

    /**
     * 在指定的时间执行
     * 将runnable放后面 方便lambda或者kotlin
     * @param  uptimeMillis 指定时间
     * @param  r runnable
     * @return 是否成功放入队列
     */
    default boolean postAtTime(long uptimeMillis,Runnable r) {
        return HANDLER.postAtTime(r, this, uptimeMillis);
    }

    /**
     * 移除单个消息回调
     * @param  r runnable
     */
    default void removeCallbacks(Runnable r) {
        HANDLER.removeCallbacks(r);
    }

    /**
     * 移除全部消息回调
     */
    default void removeCallbacks() {
        HANDLER.removeCallbacksAndMessages(this);
    }
}