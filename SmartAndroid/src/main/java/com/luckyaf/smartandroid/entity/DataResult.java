package com.luckyaf.smartandroid.entity;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/7/18
 */
/**
 * TODO: 专用于数据层返回结果给 domain 层或 ViewModel 用，原因如下：
 * <p>
 * liveData 是专用于页面开发的、用于解决生命周期安全问题的组件，
 * 有时候数据并非一定是通过 liveData 来分发给页面，也可能是通过别的组件去通知给非页面的东西，
 * 这时候 repo 方法中内定通过 liveData 分发就不太合适，不如一开始就规定不在数据层通过 liveData 返回结果。
 * <p>
 * 如果这样说还不理解的话，详见《如何让同事爱上架构模式、少写 bug 多注释》的解析
 * https://xiaozhuanlan.com/topic/8204519736
 * <p>
 * Create by KunMinX at 2020/7/20
 */
public class DataResult<T> {

    private T mEntity;
    private ResponseStatus mResponseStatus;

    public DataResult(T entity, ResponseStatus responseStatus) {
        mEntity = entity;
        mResponseStatus = responseStatus;
    }

    public T getResult() {
        return mEntity;
    }

    public ResponseStatus getResponseStatus() {
        return mResponseStatus;
    }

    public interface Result<T> {
        void onResult(DataResult<T> dataResult);
    }
}
