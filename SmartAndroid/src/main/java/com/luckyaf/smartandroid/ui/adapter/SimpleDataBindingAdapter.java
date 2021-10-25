package com.luckyaf.smartandroid.ui.adapter;

import android.content.Context;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/10/25
 */
public abstract class SimpleDataBindingAdapter<M, B extends ViewDataBinding> extends BaseDataBindingAdapter<M, B> {
    private final int layout;

    public SimpleDataBindingAdapter(Context context, int layout, @NonNull DiffUtil.ItemCallback<M> diffCallback) {
        super(context, diffCallback);
        this.layout = layout;
    }

    @Override
    @LayoutRes
    protected int getLayoutResId(int viewType) {
        return this.layout;
    }
}
