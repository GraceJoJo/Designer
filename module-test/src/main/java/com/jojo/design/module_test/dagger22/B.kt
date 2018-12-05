package com.jojo.design.module_test.dagger22

import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/11/30 9:38 PM
 *    desc   :
 */
class B {
    @Inject lateinit var a: A

    init {
        DaggerABComponent.create().Inject(this)
    }

    fun getData() = a.getData()
}
