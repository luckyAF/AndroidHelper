package com.luckyaf.smartandroid.entity;

/**
 * 类描述：基础消息
 * 前端有时候需要根据消息类型 显示不同的样式
 * @author Created by luckyAF on 2021/7/11
 */
public class CommonMessageBean {
    private String message;
    private int type;
    private CommonMessageBean(){}
    public CommonMessageBean(String message){
        this(message,0);
    }
    public CommonMessageBean(String message,int type){
        this.message = message;
        this.type=type;
    }
    public String getMessage() {
        return message;
    }
    public int getType() {
        return type;
    }
}