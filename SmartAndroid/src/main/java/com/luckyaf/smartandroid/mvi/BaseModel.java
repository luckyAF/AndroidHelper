package com.luckyaf.smartandroid.mvi;

import android.os.Bundle;
import android.os.Looper;

import com.kunminx.architecture.ui.callback.UnPeekLiveData;
import com.luckyaf.smartandroid.entity.CommonMessageBean;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/7/14
 *
 */
@SuppressWarnings("unused")
public abstract class BaseModel<I extends IIntent, S extends IViewState> extends ViewModel {

    /**
     * 获取BaseModel的名称
     */
    protected final String TAG = this.getClass().getSimpleName();

    /**
     * 页面数据
     */
    private final UnPeekLiveData<S> viewState =
            new UnPeekLiveData.Builder<S>()
            .setAllowNullValue(false)
            .create();

    /**
     * 页面事件
     */
    private final UnPeekLiveData<CommonPageEvent> pageEvent =
            new UnPeekLiveData.Builder<CommonPageEvent>()
                    .setAllowNullValue(false)
                    .create();

    /**
     * 设置最初的初始化的State
     *
     * @return 返回初始化的State
     */
    @NonNull
    abstract S initializeState();

    /**
     * 初始化数据
     * @param params 参数
     */
    public abstract void initData(@NonNull Bundle params);

    /**
     * 处理意图
     */
    public void processor(I intent){ }

    public void bindView(IntentView intentView) {
        viewState.observeSticky(intentView, intentView::render);
        pageEvent.observe(intentView,intentView::handlePageEvent);
        postState(state());
    }




    /**
     * 获取当前状态
     */
    @NonNull
    protected S state() {
        if (viewState.getValue() == null) {
            return initializeState();
        } else {
            return viewState.getValue();
        }
    }
    /**
     * 更新新状态
     */
    protected void  postState(@NonNull S s){
        if(isMainThread()){
            viewState.setValue(s);
        }else{
            viewState.postValue(s);
        }
    }
    /**
     * 发送事件
     * @param event 页面事件
     */
    protected void postEvent(@NonNull CommonPageEvent event){
        if(isMainThread()){
            pageEvent.setValue(event);
        }else{
            pageEvent.postValue(event);
        }
    }




    /**
     * 显示loading
     * @param message loading信息
     */
    protected void showLoading(String message){
        postEvent(new CommonPageEvent.ShowLoading(message));
    }
    protected void hideLoading(){
        postEvent(new CommonPageEvent.HideLoading());
    }
    protected void hideLoading(CommonMessageBean message){
        postEvent(new CommonPageEvent.HideLoading());
        showMessage(message);
    }
    protected void showMessage(String message){
        showMessage(new CommonMessageBean(message));
    }
    protected void showMessage(CommonMessageBean messageBean){
        postEvent(new CommonPageEvent.ShowMessage(messageBean));
    }

    protected boolean  isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }


}
