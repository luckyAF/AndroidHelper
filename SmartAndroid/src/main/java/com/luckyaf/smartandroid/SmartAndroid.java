package com.luckyaf.smartandroid;

import android.app.Application;

import com.luckyaf.smartandroid.config.ILogStrategy;
import com.luckyaf.smartandroid.config.IToastStrategy;
import com.luckyaf.smartandroid.entity.CommonMessageBean;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/10/22
 */
public class SmartAndroid implements IToastStrategy , ILogStrategy {

    private static class SmartAndroidHolder {
        private static final SmartAndroid SMART_ANDROID = new SmartAndroid();
    }

    public static SmartAndroid get() {
        return SmartAndroidHolder.SMART_ANDROID;
    }
    private SmartAndroid(){ }


    public Application getApp(){
        return SmartAndroidConfig.getInstance().getApp();
    }

    @Override
    public void showShort(CommonMessageBean bean) {
        SmartAndroidConfig.getInstance().getToastStrategy().showShort(bean);
    }
    @Override
    public void showShort(String message) {
        SmartAndroidConfig.getInstance().getToastStrategy().showShort(message);

    }

    @Override
    public void showLong(CommonMessageBean bean) {
        SmartAndroidConfig.getInstance().getToastStrategy().showLong(bean);

    }

    @Override
    public void showLong(String message) {
        SmartAndroidConfig.getInstance().getToastStrategy().showLong(message);
    }

    @Override
    public void print(String tag, String message) {
        SmartAndroidConfig.getInstance().getLogStrategy().print(tag,message);
    }

    @Override
    public void log(String type, String info) {
        SmartAndroidConfig.getInstance().getLogStrategy().log(type,info);

    }

    @Override
    public void clearLog() {
        SmartAndroidConfig.getInstance().getLogStrategy().clearLog();

    }

    @Override
    public void clearLogByType(String type, int saveDay) {
        SmartAndroidConfig.getInstance().getLogStrategy().clearLogByType(type,saveDay);

    }
}
