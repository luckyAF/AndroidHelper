package com.luckyaf.smartandroid.mvi;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/7/14
 */
public abstract class BaseModel<I extends IIntent ,S extends IViewState> extends ViewModel {

    /**
     * 获取TAG的activity名称
     */
    protected final String TAG = this.getClass().getSimpleName();

    /**
     * 页面数据
     */
    private final MutableLiveData viewState = new MutableLiveData<S>();

     /**
     * 设置最初的初始化的State
      * @return 返回初始化的State
     */
     @NonNull
    abstract S initializeState();



     public void bindView(){

     }


    /**
     * 获取当前状态
     */
    @SuppressWarnings("unchecked")
    @NonNull
    protected S state(){
        if(viewState.getValue() == null){
            return initializeState();
        }else{
            return (S)viewState.getValue();
        }
    }


}
