package com.luckyaf.smartandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.luckyaf.smartandroid.R;
import com.luckyaf.smartandroid.mvi.BaseModel;
import com.luckyaf.smartandroid.mvi.IntentView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/7/11
 */
@SuppressWarnings("unused")
public abstract class BaseFragment extends Fragment {
    private ViewModelProvider mFragmentProvider;
    private ViewModelProvider mActivityProvider;
    private ViewModelProvider mApplicationProvider;
    protected final String TAG = this.getClass().getName();
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    private static final String KEY_SAVE_BUNDLE = "key_fragment_save_bundle";
    private Bundle savedBundle;

    protected Context mContext;
    protected AppCompatActivity mActivity;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = (AppCompatActivity) context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            savedBundle = savedInstanceState.getBundle(KEY_SAVE_BUNDLE);
            FragmentManager manager = getParentFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            if (isSupportHidden) {
                transaction.hide(this).commitAllowingStateLoss();
            } else {
                transaction.show(this).commitAllowingStateLoss();
            }
        }
    }


    public void initData(@NonNull Bundle  bundle){
    }

    /**
     * 初始化View
     */
    public void initView(){

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        savedBundle = getArguments();
        if (savedBundle == null) {
            savedBundle = new Bundle();
        }
        initData(savedBundle);
        initView();
    }




    public void closeFromRight(){
        FragmentManager fragmentManager= getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .setCustomAnimations(R.anim.base_left_in_window, R.anim.base_right_out_window)
                .hide(this).commit();
    }
    public void closeFromBottom(){
        FragmentManager fragmentManager= getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .setCustomAnimations(R.anim.base_bottom_in_window, R.anim.base_bottom_out_window)
                .hide(this).commit();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBundle(KEY_SAVE_BUNDLE, this.savedBundle);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
        super.onSaveInstanceState(outState);

    }

    //TODO tip 2: Jetpack 通过 "工厂模式" 来实现 ViewModel 的作用域可控，
    //目前我们在项目中提供了 Application、Activity、Fragment 三个级别的作用域，
    //值得注意的是，通过不同作用域的 Provider 获得的 ViewModel 实例不是同一个，
    //所以如果 ViewModel 对状态信息的保留不符合预期，可以从这个角度出发去排查 是否眼前的 ViewModel 实例不是目标实例所致。

    //如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/6257931840

    protected <T extends ViewModel> T getFragmentScopeViewModel(@NonNull Class<T> modelClass) {
        if (mFragmentProvider == null) {
            mFragmentProvider = new ViewModelProvider(this);
        }
        T model =  mFragmentProvider.get(modelClass);
        if(model instanceof BaseModel && this instanceof IntentView){
            ((BaseModel<?, ?>) model).bindView((IntentView) this);
        }
        return model;
    }

    protected <T extends ViewModel> T getActivityScopeViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(mActivity);
        }
        T model =  mActivityProvider.get(modelClass);
        if(model instanceof BaseModel && this instanceof IntentView){
            ((BaseModel<?, ?>) model).bindView((IntentView) this);
        }
        return model;
    }


    protected <T extends ViewModel> T getApplicationScopeViewModel(@NonNull Class<T> modelClass) {
        if (mApplicationProvider == null) {
            mApplicationProvider = new ViewModelProvider((BaseApplication) mActivity.getApplicationContext());
        }
        return mApplicationProvider.get(modelClass);
    }



}
