package com.jojo.design.module_test.dagger22

import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/11/30 9:35 PM
 *    desc   :
 */
class A @Inject constructor(private val aa: Aa, private val aaa: Aaa) {
    fun getData() = aa.getData() + aaa.getData()
}