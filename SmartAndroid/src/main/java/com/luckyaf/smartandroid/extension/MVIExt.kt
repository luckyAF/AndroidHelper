package com.luckyaf.smartandroid.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlin.reflect.KProperty1

/**
 * 类描述：
 * @author Created by luckyAF on 2022/2/14
 *
 */


/**
 * 注意 使用局部更新的viewState不能混淆
 */


fun <T, A> LiveData<T>.observeState(
    lifecycleOwner: LifecycleOwner,
    prop1: KProperty1<T, A>,
    action: (A) -> Unit
) {
    this.map { StateTuple1(prop1.get(it)) }
        .distinctUntilChanged()
        .observe(lifecycleOwner) { (a) ->
            action.invoke(a)
        }
}

fun <T, A, B> LiveData<T>.observeState(
    lifecycleOwner: LifecycleOwner,
    prop1: KProperty1<T, A>,
    prop2: KProperty1<T, B>,
    action: (A, B) -> Unit
) {
    this.map { StateTuple2(prop1.get(it), prop2.get(it)) }
        .distinctUntilChanged()
        .observe(lifecycleOwner) { (a, b) ->
            action.invoke(a, b)
        }
}

fun <T, A, B, C> LiveData<T>.observeState(
    lifecycleOwner: LifecycleOwner,
    prop1: KProperty1<T, A>,
    prop2: KProperty1<T, B>,
    prop3: KProperty1<T, C>,
    action: (A, B, C) -> Unit
) {
    this.map { StateTuple3(prop1.get(it), prop2.get(it), prop3.get(it)) }
        .distinctUntilChanged()
        .observe(lifecycleOwner) { (a, b, c) ->
            action.invoke(a, b, c)
        }
}

/**
 * 局部状态,支持一个参数
 */
suspend fun <T, A> Flow<T>.collectState(prop1: KProperty1<T, A>, action: (A) -> Unit) {
    this.map { StateTuple1(prop1.get(it)) }//获取属性值
        .distinctUntilChanged() //属性值变化
        .collectLatest { (a) ->
            action.invoke(a)
        }
}

/**
 * 局部状态,支持两个参数
 */
suspend fun <T, A, B> Flow<T>.collectState(
    prop1: KProperty1<T, A>,
    prop2: KProperty1<T, B>,
    action: (A, B) -> Unit
) {
    this.map { StateTuple2(prop1.get(it), prop2.get(it)) }
        .distinctUntilChanged()
        .collectLatest { (a, b) -> action.invoke(a, b) }
}

/**
 * 局部状态,支持三个参数
 */
suspend fun <T, A, B, C> Flow<T>.collectState(
    prop1: KProperty1<T, A>,
    prop2: KProperty1<T, B>,
    prop3: KProperty1<T, C>,
    action: (A, B, C) -> Unit
) {
    this.map { StateTuple3(prop1.get(it), prop2.get(it), prop3.get(it)) }
        .distinctUntilChanged()
        .collectLatest { (a, b, c) -> action.invoke(a, b, c) }
}


internal data class StateTuple1<A>(val a: A)
internal data class StateTuple2<A, B>(val a: A, val b: B)
internal data class StateTuple3<A, B, C>(val a: A, val b: B, val c: C)
