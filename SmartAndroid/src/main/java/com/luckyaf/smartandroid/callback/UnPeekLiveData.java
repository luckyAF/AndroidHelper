package com.luckyaf.smartandroid.callback;


/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/10/29
 */
public class UnPeekLiveData<T> extends ProtectedUnPeekLiveData<T> {
    public UnPeekLiveData() {
    }
    @Override
    public void setValue(T value) {
        super.setValue(value);
    }
    @Override
    public void postValue(T value) {
        super.postValue(value);
    }

    public static class Builder<T> {
        private boolean isAllowNullValue;

        public Builder() {
        }

        public UnPeekLiveData.Builder<T> setAllowNullValue(boolean allowNullValue) {
            this.isAllowNullValue = allowNullValue;
            return this;
        }

        public UnPeekLiveData<T> create() {
            UnPeekLiveData<T> liveData = new UnPeekLiveData();
            liveData.isAllowNullValue = this.isAllowNullValue;
            return liveData;
        }
    }
}
