package com.luckyaf.smartandroid.extension
import android.util.Log
import com.luckyaf.smartandroid.AppConfig

/**
 * 类描述：
 * @author Created by luckyAF on 2021/10/25
 *
 */


fun String?.Print(tag:String){
    if(AppConfig.getInstance().isLogEnable){
        Log.i(tag,this?:"")

    }
}