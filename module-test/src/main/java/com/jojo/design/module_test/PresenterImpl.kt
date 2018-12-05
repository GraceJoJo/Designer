package com.jojo.design.module_test

import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/4 6:29 PM
 *    desc   :
 */
class PresenterImpl @Inject constructor() : MainPresenter {
    override fun getData(): String {
        return "哈哈哈哈哈"
    }
}