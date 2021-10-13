package com.luckyaf.smartandroid.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/10/13
 */
public class BaseApplication extends Application implements ViewModelStoreOwner {

    private boolean hasInitialized = false;

    private ViewModelStore mAppViewModelStore;


    public void initializeConfig(){
        mAppViewModelStore = new ViewModelStore();

    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeConfig();
    }

    public void  reInitializeConfig(){
        if(!hasInitialized){
            initializeConfig();
        }
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }
}
