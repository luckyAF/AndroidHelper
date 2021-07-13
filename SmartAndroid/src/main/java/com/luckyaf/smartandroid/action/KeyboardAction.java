package com.luckyaf.smartandroid.action;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/6/15
 */
@SuppressWarnings("unused")
public interface KeyboardAction {

    /**
     * 显示软键盘
     *  @param view view
     */
    default void showKeyboard(View view) {
        if (view == null) {
            return;
        }
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null) {
            manager.showSoftInput(view, 0);
        }
    }

    /**
     * 隐藏软键盘
     * @param view 隐藏软键盘
     */
    default void hideKeyboard(View view) {
        if (view == null) {
            return;
        }
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null) {
            manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 切换软键盘
     * @param view view
     */
    default void toggleSoftInput(View view) {
        if (view == null) {
            return;
        }
        toggleSoftInput(view.getContext());
    }
    /**
     * 切换软键盘
     * @param context context
     */
    default void toggleSoftInput(@NonNull Context context) {
        InputMethodManager manager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null) {
            manager.toggleSoftInput(0, 0);
        }
    }
}