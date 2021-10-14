package com.luckyaf.smartandroid.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/10/14
 */
public abstract class BaseBottomSheetDialogFragment extends BottomSheetDialogFragment {

    protected View mView;
    private Context mContext;

    /**
     * 返回layoutId
     * @return layoutId
     */
    public abstract int getLayoutId();

    /**
     * 初始化view
     */
    public abstract void initView();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mContext = getContext();
        mView = inflater.inflate(getLayoutId(), container, false);
        initView();
        return mView;
    }


    @Override
    public void onStart() {
        super.onStart();
        //获取dialog对象
        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
        //把window的默认背景颜色去掉，不然圆角显示不见
        assert dialog != null;
        dialog.getWindow().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackground(new ColorDrawable(Color.TRANSPARENT));
        //获取dialog的根部局
        FrameLayout bottomSheet = dialog.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (bottomSheet != null) {
            //获取根部局的LayoutParams对象
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomSheet.getLayoutParams();
            layoutParams.height = getPeekHeight();
            //修改弹窗的最大高度，不允许上滑（默认可以上滑）
            bottomSheet.setLayoutParams(layoutParams);
            final BottomSheetBehavior<FrameLayout> behavior = BottomSheetBehavior.from(bottomSheet);
            //peekHeight即弹窗的最大高度
            behavior.setPeekHeight(getPeekHeight());
            // 初始为展开状态
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        }
    }

    /**
     * 弹窗高度，默认为屏幕高度的四分之三
     * 子类可重写该方法返回peekHeight
     *
     * @return height
     */
    protected int getPeekHeight() {
        int peekHeight = getResources().getDisplayMetrics().heightPixels;
        //设置弹窗高度为屏幕高度的3/4
        return peekHeight - peekHeight / 4;
    }
}

