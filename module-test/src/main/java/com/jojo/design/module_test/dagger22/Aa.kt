package com.jojo.design.module_test.dagger22

import android.util.Log
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/11/30 9:35 PM
 *    desc   :
 */
class Aa @Inject constructor() {
    init {
        Log.e("TAG","Aa类初始化后，")

    }
    fun getData() = "在kotlin中使用注解构造方法的方式拿到值"
}