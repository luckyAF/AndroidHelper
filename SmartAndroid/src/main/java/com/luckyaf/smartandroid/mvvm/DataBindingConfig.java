package com.luckyaf.smartandroid.mvvm;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/7/16
 */

import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

/**
 * TODO tip:
 * 将 DataBinding 实例限制于 base 页面中，默认不向子类暴露，
 * 通过这样的方式，来彻底解决 视图调用的一致性问题，
 * 如此，视图实例的安全性将和基于函数式编程思想的 Jetpack Compose 持平。
 * 而 DataBindingConfig 就是在这样的背景下，用于为 base 页面中的 DataBinding 提供绑定项。
 * <p>
 * 如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910
 * <p>
 * @author  by KunMinX at 20/4/18
 */
public class DataBindingConfig {

    private final int layout;

    private final int vmVariableId;

    private final ViewModel stateViewModel;

    private final SparseArray<Object> bindingParams = new SparseArray();

    public DataBindingConfig(@NonNull Integer layout,
                             @NonNull Integer vmVariableId,
                             @NonNull ViewModel stateViewModel) {
        this.layout = layout;
        this.vmVariableId = vmVariableId;
        this.stateViewModel = stateViewModel;
    }

    public int getLayout() {
        return layout;
    }

    public int getVmVariableId() {
        return vmVariableId;
    }

    public ViewModel getStateViewModel() {
        return stateViewModel;
    }

    public SparseArray<Object> getBindingParams() {
        return bindingParams;
    }

    public DataBindingConfig addBindingParam(@NonNull Integer variableId,
                                             @NonNull Object object) {
        if (bindingParams.get(variableId) == null) {
            bindingParams.put(variableId, object);
        }
        return this;
    }
}
