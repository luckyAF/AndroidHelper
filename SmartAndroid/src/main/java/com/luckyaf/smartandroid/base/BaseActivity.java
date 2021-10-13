package com.luckyaf.smartandroid.base;

import android.content.Intent;
import android.os.Bundle;

import com.luckyaf.smartandroid.R;
import com.luckyaf.smartandroid.action.KeyboardAction;
import com.luckyaf.smartandroid.callback.OnActivityCallback;
import androidx.activity.result.contract.ActivityResultContracts;
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


    private Bundle savedBundle;
    private static final String KEY_SAVE_BUNDLE = "key_activity_save_bundle";
    /**
     * 获取TAG的activity名称
     */
    protected final String TAG = this.getClass().getSimpleName();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            savedBundle = getIntent().getExtras();
        } else {
            savedBundle = savedInstanceState.getBundle(KEY_SAVE_BUNDLE);
        }
        //如果没有任何参数，则初始化 savedBundle，避免调用时 null pointer
        if (savedBundle == null) {
            savedBundle = new Bundle();
        }
        initData(savedBundle);

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

    public void initData(Bundle bundle){

    }

    /**
     * 初始化View
     */
    public void initView(){

    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBundle(KEY_SAVE_BUNDLE, this.savedBundle);
        super.onSaveInstanceState(outState);
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
        overridePendingTransition(newActivityStartAnim(), nowActivityStopAnim());
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
