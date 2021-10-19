package com.luckyaf.smartandroid.extension

import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import java.lang.Exception
import java.lang.reflect.Method

/**
 * 类描述：
 * @author Created by luckyAF on 2021/4/21
 *
 */

/**
 * 禁止系统键盘
 */
fun EditText.disableSystemKeyboard() {
    if(Build.VERSION.SDK_INT >= 21){
        showSoftInputOnFocus = false
    } else  {
        try {
            val cls = EditText::class.java
            val setShowSoftInputOnFocus: Method =
                cls.getMethod("setShowSoftInputOnFocus", Boolean::class.javaPrimitiveType)
            setShowSoftInputOnFocus.isAccessible = true
            setShowSoftInputOnFocus.invoke(this, false)
        } catch (e: SecurityException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}


fun EditText.addEnterListener(block: () -> Unit) {
    this.setOnEditorActionListener { _, actionId, event ->
        var hasDone = false
        if (actionId == EditorInfo.IME_ACTION_SEND
            || actionId == EditorInfo.IME_ACTION_DONE
            || actionId == EditorInfo.IME_ACTION_SEARCH
            || actionId == EditorInfo.IME_ACTION_GO
            || (event != null && KeyEvent.KEYCODE_ENTER == event.keyCode
                    && KeyEvent.ACTION_UP == event.action)) {
            hasDone = true
            block()
        }
        hasDone
    }
}

fun LifecycleOwner.watchText(textView: TextView, rtn: (String) -> Unit) {
    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            rtn.invoke(s.toString())
        }
        override fun afterTextChanged(s: Editable) {}
    }
    watchText(textView,textWatcher)
}

fun LifecycleOwner.watchText(textView: TextView, textWatcher: TextWatcher) {
    textView.addTextChangedListener(textWatcher)
    lifecycle.addObserver(TextWatcherLifecycleListener(textView, textWatcher, lifecycle))
}

internal class TextWatcherLifecycleListener(private val textView: TextView, private val listener: TextWatcher, private val lifecycle: Lifecycle) :
    LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun cancelCoroutine() {
        textView.removeTextChangedListener(listener)
        lifecycle.removeObserver(this)
    }
}
