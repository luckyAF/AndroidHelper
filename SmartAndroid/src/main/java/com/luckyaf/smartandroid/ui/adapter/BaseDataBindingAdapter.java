package com.luckyaf.smartandroid.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/10/25
 */

public abstract class BaseDataBindingAdapter<M, B extends ViewDataBinding> extends ListAdapter<M, RecyclerView.ViewHolder> {
    protected Context mContext;
    protected BaseDataBindingAdapter.OnItemClickListener<M> mOnItemClickListener;
    protected BaseDataBindingAdapter.OnItemLongClickListener<M> mOnItemLongClickListener;

    public void setOnItemClickListener(BaseDataBindingAdapter.OnItemClickListener<M> onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(BaseDataBindingAdapter.OnItemLongClickListener<M> onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public BaseDataBindingAdapter(Context context, @NonNull DiffUtil.ItemCallback<M> diffCallback) {
        super(diffCallback);
        this.mContext = context;
    }

    @Override
    public void submitList(@Nullable List<M> list) {
        super.submitList(list, () -> {
            super.submitList(list == null ? new ArrayList() : new ArrayList(list));
        });
    }

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        B binding = DataBindingUtil.inflate(LayoutInflater.from(this.mContext), this.getLayoutResId(viewType), parent, false);
        BaseDataBindingAdapter.BaseBindingViewHolder holder = new BaseDataBindingAdapter.BaseBindingViewHolder(binding.getRoot());
        holder.itemView.setOnClickListener((v) -> {
            if (this.mOnItemClickListener != null) {
                int position = holder.getBindingAdapterPosition();
                this.mOnItemClickListener.onItemClick(holder.itemView.getId(), this.getItem(position), position);
            }

        });
        holder.itemView.setOnLongClickListener((v) -> {
            if (this.mOnItemLongClickListener != null) {
                int position = holder.getBindingAdapterPosition();
                this.mOnItemLongClickListener.onItemLongClick(holder.itemView.getId(), this.getItem(position), position);
                return true;
            } else {
                return false;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        B binding = DataBindingUtil.getBinding(holder.itemView);
        this.onBindItem(binding, this.getItem(position), holder);
        if (binding != null) {
            binding.executePendingBindings();
        }

    }
    @LayoutRes
    protected abstract int getLayoutResId(int viewType);

    protected abstract void onBindItem(B binding, M item, RecyclerView.ViewHolder holder);

    public interface OnItemLongClickListener<M> {
        void onItemLongClick(int viewId, M item, int position);
    }

    public interface OnItemClickListener<M> {
        void onItemClick(int viewId, M item, int position);
    }

    public static class BaseBindingViewHolder extends RecyclerView.ViewHolder {
        BaseBindingViewHolder(View itemView) {
            super(itemView);
        }
    }
}
