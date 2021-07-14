package com.luckyaf.smartandroid.mvi;

import com.luckyaf.smartandroid.entity.CommonMessageBean;

/**
 * 类描述：通用页面事件
 *
 * @author Created by luckyAF on 2021/7/14
 */
public class CommonPageEvent {
    /**
     * 用于区分事件是发送给谁的
     */
    protected String tag = "";
    public String getTag() {
        return tag;
    }

    /**
     * 显示loading
     */
    public static class ShowLoading extends CommonPageEvent{
        private final String message;
        public String getMessage() {
            return message;
        }
        public ShowLoading(String message){
            this("",message);
        }
        public ShowLoading(String tag,String message){
            this.tag = tag;
            this.message = message;
        }
    }
    /**
     * 隐藏loading
     */
    public static class HideLoading extends CommonPageEvent{
        public HideLoading(){
            this("");
        }
        public HideLoading(String tag){
            this.tag = tag;
        }
    }

    /**
     * 隐藏loading
     */
    public static class ShowMessage extends CommonPageEvent{
        private final CommonMessageBean messageBean;

        public CommonMessageBean getMessageBean() {
            return messageBean;
        }
        public ShowMessage(String message){
            this("",new CommonMessageBean(message));
        }
        public ShowMessage(CommonMessageBean message){
            this("",message);
        }
        public ShowMessage(String tag, CommonMessageBean messageBean){
            this.tag = tag;
            this.messageBean = messageBean;

        }
    }

}
