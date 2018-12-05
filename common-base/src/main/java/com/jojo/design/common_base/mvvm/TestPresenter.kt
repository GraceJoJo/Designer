package com.jojo.design.common_base.mvvm

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/3 4:49 PM
 *    desc   : 普通MVP- Presenter
 */
class TestPresenter : TestContract.Presenter() {
    override fun showToast() {
        (mView as TestContract.View)?.showToast((mModel as TestModel)?.showToast())

    }
}