package com.luckyaf.smartandroid.config;

/**
 * 类描述：打印策略
 *
 * @author Created by luckyAF on 2021/10/22
 */
public interface ILogStrategy {

    /**
     * 打印日志
     */
    void print(String tag,String message);


    void log(String type,String info);


    default void clearLog(){

    }
    default void clearLogByType(String type,int saveDay){

    }


}
