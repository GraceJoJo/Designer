package com.jojo.design.common_base.mvvm

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/3 4:53 PM
 *    desc   : 普通MVP- Model层
 */
class TestModel : TestContract.Model {
    override fun showToast() = "我是Model层的普通MVP"
}