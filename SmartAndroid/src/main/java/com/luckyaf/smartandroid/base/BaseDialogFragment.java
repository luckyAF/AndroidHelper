package com.luckyaf.smartandroid.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

/**
 * 类描述： 基础dialogFragment
 * 用于某些数据比较复杂的页面 可用作通用数据页面，全屏显示
 *
 * @author Created by luckyAF on 2021/7/11
 */
@SuppressWarnings("unused")
public abstract class BaseDialogFragment extends DialogFragment {

    public   String TAG = getClass().getSimpleName();
    private static final float DEFAULT_DIM_AMOUNT = 0.2F;

    /**
     * 获取布局id
     * @return id
     */
    protected abstract int getLayoutRes();

    protected abstract void bindView(View view);


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(getLayoutRes(), container, false);
        bindView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //去除Dialog默认头部
        Dialog dialog = getDialog();
        if(dialog!=null){
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCanceledOnTouchOutside(isCancelableOutside());
            if (dialog.getWindow() != null && getDialogAnimationRes() > 0) {
                dialog.getWindow().setWindowAnimations(getDialogAnimationRes());
            }
            if (getOnKeyListener() !=null){
                dialog.setOnKeyListener(getOnKeyListener());
            }
        }

    }

    protected DialogInterface.OnKeyListener getOnKeyListener() {
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            //设置窗体背景色透明
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置宽高
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            if (getDialogWidth() > 0) {
                layoutParams.width = getDialogWidth();
            } else {
                layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            }
            if (getDialogHeight() > 0) {
                layoutParams.height = getDialogHeight();
            } else {
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            }
            //透明度
            layoutParams.dimAmount = getDimAmount();
            //位置
            layoutParams.gravity = getGravity();
            window.setAttributes(layoutParams);
        }
    }

    //默认弹窗位置为中心
    public int getGravity() {
        return Gravity.CENTER;
    }

    //默认宽高为包裹内容
    public int getDialogHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    public int getDialogWidth() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    /**
     * 默认透明度为0.2
     * @return 透明度
     */
    public float getDimAmount() {
        return DEFAULT_DIM_AMOUNT;
    }

    public String getFragmentTag() {
        return TAG;
    }

    public void showDialogFragment(FragmentManager fragmentManager) {
        show(fragmentManager, getFragmentTag());
    }

    protected boolean isCancelableOutside() {
        return true;
    }

    /**
     * 获取弹窗显示动画,子类实现
     * @return 动画
     */
    public int getDialogAnimationRes() {
        return 0;
    }

    //获取设备屏幕宽度
    public  static  int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    //获取设备屏幕高度
    public  static  int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
