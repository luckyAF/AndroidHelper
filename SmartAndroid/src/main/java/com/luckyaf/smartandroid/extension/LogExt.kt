package com.luckyaf.smartandroid.extension
import android.util.Log
import com.blankj.utilcode.util.GsonUtils
import com.luckyaf.smartandroid.SmartAndroidConfig
import com.luckyaf.smartandroid.utils.Logger

/**
 * 类描述：
 * @author Created by luckyAF on 2021/10/25
 *
 */




fun String?.Print(tag:String){
    if(SmartAndroidConfig.getInstance().isLogEnable){
        Log.i(tag,this?:"")

    }
}
fun String?.PrintLog(tag:String){
    if(SmartAndroidConfig.getInstance().isLogEnable){
        Log.d(tag,this?:"")
    }
}

fun < T : Any> T?.VERBOSE(tag: String = this.toString()) {
    if (this == null) {
        Logger.v("value is null", tag)
        return
    }
    if(!SmartAndroidConfig.isLogEnable()){
        return
    }
    Logger.v(GsonUtils.toJson(this), tag)
}
fun < T : Any> T?.INFO(tag: String = this.toString()) {
    if (this == null) {
        Logger.i("value is null", tag)
        return
    }
    if(!SmartAndroidConfig.isLogEnable()){
        return
    }
    Logger.i(GsonUtils.toJson(this), tag)
}
fun < T : Any> T?.WARN(tag: String = this.toString()) {
    if (this == null) {
        Logger.w("value is null", tag)
        return
    }
    if(!SmartAndroidConfig.Debug()){
        return
    }
    Logger.w(GsonUtils.toJson(this), tag)
}
fun < T : Any> T?.DEBUG(tag: String = this.toString()) {
    if (this == null) {
        Logger.d("value is null", tag)
        return
    }
    if(!SmartAndroidConfig.Debug()){
        return
    }
    Logger.d(GsonUtils.toJson(this), tag)
}
fun < T : Any> T?.ERROR(tag: String = this.toString()) {
    if (this == null) {
        Logger.e("value is null", tag)
        return
    }
    if(!SmartAndroidConfig.Debug()){
        return
    }
    Logger.e(GsonUtils.toJson(this), tag)
}
