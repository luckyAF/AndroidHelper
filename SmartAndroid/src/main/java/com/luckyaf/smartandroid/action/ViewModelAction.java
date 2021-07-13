package com.luckyaf.smartandroid.action;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/7/11
 */
public interface ViewModelAction {

    /**
     * 获取activity级viewmodel
     * @param modelClass
     * @param <T>
     * @return
     */
     <T extends ViewModel> T getActivityScopeViewModel(@NonNull Class<T> modelClass);

    /**
     *
     * @param modelClass
     * @param <T>
     * @return
     */
     <T extends ViewModel> T getApplicationScopeViewModel(@NonNull Class<T> modelClass);

}
