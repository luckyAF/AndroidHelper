package com.luckyaf.smartandroid.config;

import com.luckyaf.smartandroid.entity.CommonMessageBean;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/10/22
 */
public interface IToastStrategy {

    /**
     * 显示短消息
     * @param bean 消息bean
     */
   default void showShort(CommonMessageBean bean) {
       showShort(bean.getMessage());
   }

    /**
     * 显示短消息
     * @param message message
     */
   void showShort(String message);

    /**
     * 显示消息
     * @param bean 消息bean
     */
    default void showLong(CommonMessageBean bean) {
        showLong(bean.getMessage());
    }

    /**
     * 显示长消息
     * @param message message
     */
    void showLong(String message);



}
