package com.luckyaf.smartandroid.config;

import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/10/22
 */
public class DefaultToastStrategy implements IToastStrategy {

    @Override
    public void showShort(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void showLong(String message) {
        ToastUtils.showLong(message);

    }
}
