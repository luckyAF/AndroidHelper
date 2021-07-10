package com.luckyaf.baseandroid.callback;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 类描述：activity页面跳转回调
 *  替换SmartJump,可以使页面跳转更流畅
 *
 * @author Created by luckyAF on 2021/6/9
 */
public interface OnActivityCallback {

    /**
     * 结果回调
     *
     * @param resultCode        结果码
     * @param data              数据
     */
    void onActivityResult(@NonNull int resultCode, @Nullable Intent data);
}