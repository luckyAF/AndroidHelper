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
public final class AppConfig {

    private static volatile AppConfig sConfig;

    public static AppConfig getInstance() {
        if (sConfig == null) {
            // 当前没有初始化配置
            throw new IllegalStateException("You haven't initialized the configuration yet");
        }
        return sConfig;
    }

    private static void setInstance(AppConfig config) {
        sConfig = config;
    }

    public static AppConfig with(Application app) {
        return new AppConfig(app);
    }

    private AppConfig(Application app){
        mApp = app;
    }

    private final Application mApp;
    private IToastStrategy mToastStrategy;
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

    public AppConfig setToastStrategy(IToastStrategy toastStrategy){
        mToastStrategy = toastStrategy;
        return this;
    }

    public void setLogStrategy(ILogStrategy logStrategy) {
        this.mLogStrategy = logStrategy;
    }

    public void init(){
        if(mToastStrategy==null){
            mToastStrategy = new DefaultToastStrategy();
        }
        if(mLogStrategy==null){
            mLogStrategy = new DefaultLogStrategy();
        }
        AppConfig.setInstance(this);
    }
}
