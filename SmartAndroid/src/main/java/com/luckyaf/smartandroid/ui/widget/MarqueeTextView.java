package com.luckyaf.smartandroid.ui.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/7/23
 */
public class MarqueeTextView extends AppCompatTextView {
    public MarqueeTextView(Context context) {
        super(context);
        initMarquee();
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMarquee();
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMarquee();
    }
    private void initMarquee(){
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setSingleLine(true);
        setSelected(true);
        setFocusable(true);
        //setFocusableInTouchMode(true);
        setEllipsize(TextUtils.TruncateAt.MARQUEE);

    }

    @Override
    public boolean isFocused() {
        return true;
    }
}

