package com.jojo.design.module_test.dagger22

import android.util.Log
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/11/30 9:37 PM
 *    desc   :
 */
class Aaa @Inject constructor(private val aa: Aa) {
    init {
        Log.e("TAG","Aaa类初始化后，")
    }

    fun getData() = "在Aaa中获取到Aa的数据:" + aa.getData()
}