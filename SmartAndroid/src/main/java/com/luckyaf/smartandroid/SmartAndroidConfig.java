package com.luckyaf.smartandroid;

import android.app.Application;

import com.luckyaf.smartandroid.config.DefaultLogStrategy;
import com.luckyaf.smartandroid.config.DefaultToastStrategy;
import com.luckyaf.smartandroid.config.ILogStrategy;
import com.luckyaf.smartandroid.config.IToastStrategy;

/**
 * 类描述：应用配置
 *
 * @author Created by luckyAF on 2021/10/22
 */
public final class SmartAndroidConfig {

    private static volatile SmartAndroidConfig sConfig;

    public static SmartAndroidConfig getInstance() {
        if (sConfig == null) {
            // 当前没有初始化配置
            throw new IllegalStateException("You haven't initialized the configuration yet");
        }
        return sConfig;
    }

    private static void setInstance(SmartAndroidConfig config) {
        sConfig = config;
    }

    public static SmartAndroidConfig with(Application app) {
        return new SmartAndroidConfig(app);
    }

    private SmartAndroidConfig(Application app){
        mApp = app;
    }

    private final Application mApp;
    private IToastStrategy mToastStrategy;
    private boolean mLogEnable;
    private ILogStrategy mLogStrategy;

    public Application getApp() {
        return mApp;
    }

    public IToastStrategy getToastStrategy() {
        return mToastStrategy;
    }
    public ILogStrategy getLogStrategy() {
        return mLogStrategy;
    }

    public SmartAndroidConfig setToastStrategy(IToastStrategy toastStrategy){
        mToastStrategy = toastStrategy;
        return this;
    }

    public SmartAndroidConfig setLogEnable(boolean logEnable) {
        this.mLogEnable = logEnable;
        return this;
    }

    public boolean isLogEnable() {
        return mLogEnable;
    }

    public void setLogStrategy(ILogStrategy logStrategy) {
        this.mLogStrategy = logStrategy;
    }

    public void init(){
        if(mToastStrategy==null){
            mToastStrategy = new DefaultToastStrategy();
        }
        if(mLogStrategy==null){
            mLogStrategy = new DefaultLogStrategy(mApp);
        }
        SmartAndroidConfig.setInstance(this);
    }
}
