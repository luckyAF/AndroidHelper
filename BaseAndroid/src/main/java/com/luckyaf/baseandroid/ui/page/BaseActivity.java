package com.luckyaf.baseandroid.ui.page;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;

import com.luckyaf.baseandroid.action.KeyboardAction;
import com.luckyaf.baseandroid.callback.OnActivityCallback;

import java.util.Random;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 类描述：通用基础activity
 *
 * @author Created by luckyAF on 2021/7/11
 */
public abstract class BaseActivity extends AppCompatActivity implements KeyboardAction {
    /** Activity 回调集合 */
    private SparseArray<OnActivityCallback> mActivityCallbacks;
    /**
     * 获取TAG的activity名称
     */
    protected final String TAG = this.getClass().getSimpleName();

    /**
     * 布局id
     *
     * @return 布局id
     */
    protected abstract @LayoutRes int getLayoutId();


    protected void initParams(Bundle bundle){
    }

    /**
     * 初始化羽绒棉
     */
    protected void initView(){

    }

    /**
     * 初始化数据
     */
    protected void initData(){

    }


    /**
     * 跳转
     * @param intent intent
     * @param options options
     * @param callback 回调
     */
    public void startActivityForResult(Intent intent, @Nullable Bundle options, OnActivityCallback callback) {
        if (mActivityCallbacks == null) {
            mActivityCallbacks = new SparseArray<>(1);
        }
        // 请求码必须在 2 的 16 次方以内
        int requestCode = new Random().nextInt((int) Math.pow(2, 16));
        mActivityCallbacks.put(requestCode, callback);
        startActivityForResult(intent, requestCode, options);
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        // 隐藏软键，避免内存泄漏
        hideKeyboard(getCurrentFocus());
        // 查看源码得知 startActivity 最终也会调用 startActivityForResult
        super.startActivityForResult(intent, requestCode, options);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        OnActivityCallback callback;
        if (mActivityCallbacks != null && (callback = mActivityCallbacks.get(requestCode)) != null) {
            callback.onActivityResult(resultCode, data);
            mActivityCallbacks.remove(requestCode);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
