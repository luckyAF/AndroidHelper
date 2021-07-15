package com.luckyaf.smartandroid.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luckyaf.smartandroid.mvi.BaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 类描述：
 * @author Created by luckyAF on 2021/7/15
 *
 */

fun ViewModel.runOnIO(block: () -> Unit){
    viewModelScope.launch {
        withContext(Dispatchers.IO) {
            block()
        }
    }
}