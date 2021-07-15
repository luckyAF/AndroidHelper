package com.luckyaf.smartandroid.mvi;

import com.luckyaf.smartandroid.ui.page.BaseView;

import java.util.Map;

import androidx.lifecycle.LifecycleOwner;

import static java.util.Collections.emptyMap;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/7/15
 */
public interface IntentView  extends LifecycleOwner {
    /**
     * 返回 意图map
     *
     * @return 意图map
     */
    default Map<String, IIntent> provideIntents() {
        return emptyMap();
    }

    /**
     * 处理intent(测试时使用)
     *
     * @param intent 意图
     */
    default void processor(IIntent intent) {
    }

    /**
     * 根据ViewState 渲染 页面
     *
     * @param state 页面数据
     */
    void render(Object state);


    /**
     * 处理UI事件
     * 比如跳转Activity,显示dialog,
     * @param event 页面
     */
    void handlePageEvent(CommonPageEvent event);

    /**
     *
     * System.identityHashCode(fragment.getViewModelStore());
     * @return 返回 页面特征
     */
    int getIdentity();




}
