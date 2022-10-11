package com.luckyaf.smartandroid.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;

import com.luckyaf.smartandroid.R;
import com.luckyaf.smartandroid.callback.OnActivityCallback;
import com.luckyaf.smartandroid.mvi.BaseModel;
import com.luckyaf.smartandroid.mvi.IntentView;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * 类描述：通用基础activity
 *
 * @author Created by luckyAF on 2021/7/11
 */
@SuppressWarnings("unused")
public abstract class BaseActivity extends AppCompatActivity{

    private ViewModelProvider mActivityProvider;
    private ViewModelProvider mApplicationProvider;
    private Bundle savedBundle;
    private static final String KEY_SAVE_BUNDLE = "key_activity_save_bundle";
    /**
     * 获取TAG的activity名称
     */
    protected final String TAG = this.getClass().getSimpleName();

    /** Activity 回调集合 */
    private SparseArray<OnActivityCallback> mActivityCallbacks;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            savedBundle = getIntent().getExtras();
            //如果没有任何参数，则初始化 savedBundle，避免调用时 null pointer
            if (savedBundle == null) {
                savedBundle = new Bundle();
            }
            initData(savedBundle);
        } else {
            savedBundle = savedInstanceState.getBundle(KEY_SAVE_BUNDLE);
            reInitData(savedBundle);
        }
        doBeforeSetContentView();
        doSetContentView();
        initView();

    }

    public void doBeforeSetContentView(){

    }

    /**
     * 设置view
     */
    public abstract void doSetContentView();

    public void initData(@NonNull Bundle bundle){

    }
    public void reInitData(Bundle bundle){

    }


    /**
     * 初始化View
     */
    public void initView(){

    }
    private Boolean isFirstStart = true;

    /**
     * 页面首次展示
     */
    public void firstStart(){

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isFirstStart){
            firstStart();
            isFirstStart = false;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBundle(KEY_SAVE_BUNDLE, this.savedBundle);
        super.onSaveInstanceState(outState);
    }

    /**
     * startActivityForResult 方法优化
     */

    public void startActivityForResult(Class<? extends Activity> clazz, OnActivityCallback callback) {
        startActivityForResult(new Intent(this, clazz), null, callback);
    }

    public void startActivityForResult(Intent intent, OnActivityCallback callback) {
        startActivityForResult(intent, null, callback);
    }

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        OnActivityCallback callback;
        if (mActivityCallbacks != null && (callback = mActivityCallbacks.get(requestCode)) != null) {
            callback.onActivityResult(resultCode, data);
            mActivityCallbacks.remove(requestCode);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        // 查看源码得知 startActivity 最终也会调用 startActivityForResult
        super.startActivityForResult(intent, requestCode, options);
        overridePendingTransition(newActivityStartAnim(), nowActivityStopAnim());
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(oldActivityRestartAnim(), nowActivityDestroyAnim());
    }


    //TODO tip 2: Jetpack 通过 "工厂模式" 来实现 ViewModel 的作用域可控，
    //目前我们在项目中提供了 Application、Activity、Fragment 三个级别的作用域，
    //值得注意的是，通过不同作用域的 Provider 获得的 ViewModel 实例不是同一个，
    //所以如果 ViewModel 对状态信息的保留不符合预期，可以从这个角度出发去排查 是否眼前的 ViewModel 实例不是目标实例所致。

    //如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/6257931840

    protected <T extends ViewModel> T getActivityScopeViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(this);
        }
        T model =  mActivityProvider.get(modelClass);
        if(model instanceof BaseModel && this instanceof IntentView){
            ((BaseModel<?, ?>) model).bindView((IntentView) this);
        }
        return model;
    }

    protected <T extends ViewModel> T getApplicationScopeViewModel(@NonNull Class<T> modelClass) {
        if (mApplicationProvider == null) {
            mApplicationProvider = new ViewModelProvider((BaseApplication) this.getApplicationContext());
        }
        return mApplicationProvider.get(modelClass);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        BaseApplication app = (BaseApplication) getApplicationContext();
        if (hasFocus) {
            app.reInitializeConfig();
        }
    }


    public int nowActivityStopAnim() {
        return R.anim.base_left_out_window;
    }

    public int newActivityStartAnim() {
        return R.anim.base_right_in_window;
    }

    public int nowActivityDestroyAnim() {
        return R.anim.base_right_out_window;
    }

    public int oldActivityRestartAnim() {
        return R.anim.base_left_in_window;
    }





}
