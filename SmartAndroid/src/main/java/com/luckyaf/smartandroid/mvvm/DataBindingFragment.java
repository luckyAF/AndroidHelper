package com.luckyaf.smartandroid.mvvm;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luckyaf.smartandroid.R;
import com.luckyaf.smartandroid.base.BaseApplication;
import com.luckyaf.smartandroid.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/10/13
 */
@SuppressWarnings("unused")
public abstract class DataBindingFragment extends BaseFragment {
    private ViewModelProvider mFragmentProvider;
    private ViewModelProvider mActivityProvider;
    private ViewModelProvider mApplicationProvider;

    private ViewDataBinding mBinding;
    private TextView mTvStrictModeTip;



    protected abstract DataBindingConfig getDataBindingConfig();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DataBindingConfig dataBindingConfig = this.getDataBindingConfig();
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, dataBindingConfig.getLayout(), container, false);
        binding.setLifecycleOwner(this.getViewLifecycleOwner());
        binding.setVariable(dataBindingConfig.getVmVariableId(), dataBindingConfig.getStateViewModel());
        SparseArray<Object> bindingParams = dataBindingConfig.getBindingParams();
        int i = 0;

        for(int length = bindingParams.size(); i < length; ++i) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }

        this.mBinding = binding;
        return binding.getRoot();
    }


    protected ViewDataBinding getBinding() {
        if (this.isDebug() && this.mBinding != null && this.mTvStrictModeTip == null) {
            this.mTvStrictModeTip = new TextView(this.getContext());
            this.mTvStrictModeTip.setAlpha(0.5F);
            this.mTvStrictModeTip.setPadding(this.mTvStrictModeTip.getPaddingLeft() + 24, this.mTvStrictModeTip.getPaddingTop() + 64, this.mTvStrictModeTip.getPaddingRight() + 24, this.mTvStrictModeTip.getPaddingBottom() + 24);
            this.mTvStrictModeTip.setGravity(1);
            this.mTvStrictModeTip.setTextSize(10.0F);
            this.mTvStrictModeTip.setBackgroundColor(-1);
            String tip = this.getString(R.string.debug_databinding_warning, this.getClass().getSimpleName());
            this.mTvStrictModeTip.setText(tip);
            ((ViewGroup)this.mBinding.getRoot()).addView(this.mTvStrictModeTip);
        }

        return this.mBinding;
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
        return mFragmentProvider.get(modelClass);
    }

    protected <T extends ViewModel> T getActivityScopeViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(mActivity);
        }
        return mActivityProvider.get(modelClass);
    }


    protected <T extends ViewModel> T getApplicationScopeViewModel(@NonNull Class<T> modelClass) {
        if (mApplicationProvider == null) {
            mApplicationProvider = new ViewModelProvider((BaseApplication) mActivity.getApplicationContext());
        }
        return mApplicationProvider.get(modelClass);
    }
    public boolean isDebug() {
        return this.mActivity.getApplicationContext().getApplicationInfo() != null && (this.mActivity.getApplicationContext().getApplicationInfo().flags & 2) != 0;
    }

}
