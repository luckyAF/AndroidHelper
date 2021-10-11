package com.luckyaf.smartandroid.ui.page;

import android.content.Intent;
import android.os.Bundle;

import com.luckyaf.smartandroid.action.KeyboardAction;
import com.luckyaf.smartandroid.callback.OnActivityCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

/**
 * 类描述：通用基础activity
 *
 * @author Created by luckyAF on 2021/7/11
 */
@SuppressWarnings("unused")
public abstract class BaseActivity extends AppCompatActivity implements KeyboardAction {

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
     * 初始化View
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
    public void startActivityForResult(Intent intent, @Nullable ActivityOptionsCompat options, final OnActivityCallback callback) {
        registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            int resultCode = result.getResultCode();
            callback.onActivityResult(result.getResultCode(),result.getData());
        }).launch(intent,options);
    }


}
