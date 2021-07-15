package com.luckyaf.smartandroid.ui.page;

import com.luckyaf.smartandroid.entity.CommonMessageBean;

import androidx.lifecycle.LifecycleOwner;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/7/15
 */
public interface BaseView extends LifecycleOwner {
    /**
     * 显示加载提示
     * @param message message
     */
    void showLoading(String message);

    /**
     * 结束加载提示
     */
    void hideLoading();

    /**
     * 显示信息
     *
     * @param message 消息内容
     */
    void showMessage(CommonMessageBean message);



}
