package com.luckyaf.smartandroid.extension

/**
 * 类描述：
 * @author Created by luckyAF on 2021/4/21
 *
 */



fun  <T:Any> T.addTo(collection: MutableCollection<T>?){
    collection?.add(this)
}


fun <T:Any> List<T>.copyList():ArrayList<T>{
    val list = ArrayList<T>()
    list.addAll(this)
    return list
}


fun <T:Any> Array<T>.addAllTo(collection: MutableCollection<T>?){
    collection?.addAll(this)
}

fun <T:Any> Collection<T>.addAllTo(collection: MutableCollection<T>?){
    collection?.addAll(this)
}

fun <T:Any> T.isMemberOf(collection: MutableCollection<T>?) : Boolean{
    collection?:return false
    return collection.contains(this)

}
