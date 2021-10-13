package com.luckyaf.smartandroid.mvi;

import androidx.annotation.NonNull;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/10/13
 */
public abstract class SimpleModel extends BaseModel<IIntent,IViewState>{
    @NonNull
    @Override
    IViewState initializeState() {
        return new EmptyViewState();
    }
}
