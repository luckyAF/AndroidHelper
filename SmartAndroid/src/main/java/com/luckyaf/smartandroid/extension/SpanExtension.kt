package com.luckyaf.smartandroid.extension

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.*
import android.view.View
import android.widget.TextView

/**
 * 类描述：
 * @author Created by luckyAF on 2021/4/21
 *
 */


/**
 * 将一段文字中指定range的文字改变大小
 * @param range 要改变大小的文字的范围
 * @param scale 缩放值，大于1，则比其他文字大；小于1，则比其他文字小；默认是1.5
 */
fun CharSequence.toSizeSpan(range: IntRange, scale: Float = 1.5f): CharSequence {
    return SpannableString(this).apply {
        setSpan(RelativeSizeSpan(scale), range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
    }
}

/**
 * 将一段文字中指定range的文字改变前景色
 * @param range 要改变前景色的文字的范围
 * @param color 要改变的颜色，默认是红色
 */
fun CharSequence.toColorSpan(range: IntRange, color: Int = Color.RED): CharSequence {
    return SpannableString(this).apply {
        setSpan(ForegroundColorSpan(color), range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
    }
}

/**
 * 将一段文字中指定range的文字改变背景色
 * @param range 要改变背景色的文字的范围
 * @param color 要改变的颜色，默认是红色
 */
fun CharSequence.toBackgroundColorSpan(range: IntRange, color: Int = Color.RED): CharSequence {
    return SpannableString(this).apply {
        setSpan(BackgroundColorSpan(color), range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
    }
}

/**
 * 将一段文字中指定range的文字添加删除线
 * @param range 要添加删除线的文字的范围
 */
fun CharSequence.toStrikeThroughSpan(range: IntRange): CharSequence {
    return SpannableString(this).apply {
        setSpan(StrikethroughSpan(), range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
    }
}

/**
 * 将一段文字中指定range的文字添加颜色和点击事件
 * @param range 目标文字的范围
 */
fun CharSequence.toClickSpan(range: IntRange, color: Int = Color.RED, isUnderlineText: Boolean = false, clickAction: () -> Unit): CharSequence {
    return SpannableString(this).apply {
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                clickAction()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.color = color
                ds.isUnderlineText = isUnderlineText
            }
        }
        setSpan(clickableSpan, range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
    }
}

fun CharSequence.toSpan(range: IntRange,
                        scale: Float? = null,
                        color: Int? = null,
                        backgroundColor: Int? = null,
                        style: Int? = null,
                        deleteLine: Boolean = false,
                        underLine: Boolean = false,
                        clickAction: (() -> Unit)? = null
): CharSequence {
    return SpannableString(this).apply {
        scale?.let {
            setSpan(RelativeSizeSpan(it), range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        color?.let {
            setSpan(ForegroundColorSpan(it), range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        style?.let {
            setSpan(StyleSpan(it), range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        backgroundColor?.let {
            setSpan(BackgroundColorSpan(it), range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        if (deleteLine) {
            setSpan(StrikethroughSpan(), range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        if (underLine) {
            setSpan(UnderlineSpan(), range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        clickAction?.let {
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    clickAction()
                }
            }
            setSpan(clickableSpan, range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
    }
}

/** TextView的扩展 **/
fun TextView.appendTextWithSpan(text: String = "",
                                scale: Float? = null,
                                color: Int? = null,
                                backgroundColor: Int? = null,
                                style: Int? = null,
                                deleteLine: Boolean = false,
                                underLine: Boolean = false,
                                clickAction: (() -> Unit)? = null): TextView {
    append(text.toSpan(0..text.length, scale, color, backgroundColor, style, deleteLine, underLine, clickAction))
    return this
}