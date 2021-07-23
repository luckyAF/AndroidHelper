package com.luckyaf.smartandroid.callback;

import android.os.SystemClock;
import android.view.View;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/7/23
 */
public abstract class MultipleClickListener implements View.OnClickListener {
    private int needClick = 9999;
    private long interval = 1000;
    private long[] mHits;

    public MultipleClickListener(int count){
        this.needClick = count;
        mHits = new long[needClick];
    }
    public MultipleClickListener(int count,long interval){
        this.needClick = count;
        this.interval = interval;
        mHits = new long[needClick];

    }

    @Override
    public void onClick(View v) {
        //每次点击时，数组向前移动一位
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        //为数组最后一位赋值
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        if (mHits[0] >= (SystemClock.uptimeMillis() - interval)) {
            //重新初始化数组
            mHits = new long[needClick];
            doSomeThing();
        }
    }

    /**
     * 做些啥
     */
    public abstract void doSomeThing();


}
