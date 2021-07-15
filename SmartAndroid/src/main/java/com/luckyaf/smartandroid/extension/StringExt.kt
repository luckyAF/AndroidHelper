package com.luckyaf.smartandroid.extension

/**
 * 类描述：
 * @author Created by luckyAF on 2021/4/21
 *
 */
fun String.safeSubstring(startIndex: Int, endIndex: Int = this.length) :String{
    if(startIndex > length){
        return ""
    }
    if(endIndex < 0){
        return ""
    }
    val start = if (startIndex < 0) {
        0
    } else {
        startIndex
    }
    val end = if (endIndex > length) {
        length
    }else{
        endIndex
    }
    return if(start > end){
        this.substring(end,start).reversed()
    }else{
        this.substring(start,end)
    }
}

