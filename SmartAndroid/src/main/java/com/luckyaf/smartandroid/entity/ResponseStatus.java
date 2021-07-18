package com.luckyaf.smartandroid.entity;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/7/18
 */
public class ResponseStatus {

    private String responseCode = "";
    private boolean success = true;
    private ResultSource source = ResultSource.NETWORK;

    public ResponseStatus() {
    }

    public ResponseStatus(String responseCode, boolean success) {
        this.responseCode = responseCode;
        this.success = success;
    }

    public ResponseStatus(String responseCode, boolean success, ResultSource source) {
        this(responseCode, success);
        this.source = source;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public Enum getSource() {
        return source;
    }
}
