package com.luckyaf.smartandroid.ui.binding_adapter;

import android.os.SystemClock;
import android.view.View;

import androidx.databinding.BindingAdapter;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/10/11
 */
public class CommonBindingAdapter {

    @BindingAdapter({"android:onSingleClick", "android:clickable"})
    public static void onSingleClick(View view, View.OnClickListener clickListener,
                                  boolean clickable) {
        setOnSingleClick(view, clickListener);
        view.setClickable(clickable);
    }

    @BindingAdapter({"android:onSingleClick"})
    public static void setOnSingleClick(View view, final View.OnClickListener clickListener) {
        final long[] mHits = new long[2];
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                if (mHits[0] < (SystemClock.uptimeMillis() - 500)) {
                    clickListener.onClick(v);
                }
            }
        });
    }
    @BindingAdapter({"android:MultipleClick","android:multipleClickSize"})
    public static void setMultipleClick(View view,
                                        final View.OnClickListener clickListener,
                                        int multipleClickSize
                                        ) {
        final long[] mHits = new long[multipleClickSize];
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                if (mHits[0] >= (SystemClock.uptimeMillis() - 1000)) {
                    System.arraycopy(new long[multipleClickSize], 0, mHits, 0, mHits.length);
                    clickListener.onClick(v);
                }
            }
        });
    }





}
