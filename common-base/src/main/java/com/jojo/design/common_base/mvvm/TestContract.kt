package com.jojo.design.common_base.mvvm

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/3 4:49 PM
 *    desc   : 普通MVP- 契约类
 */
class TestContract {
    interface View : IBaseView {
        fun showToast(text: String)
    }

    interface Model : IBaseModel {
        fun showToast(): String
    }

    abstract class Presenter : BasePresenter<IBaseView, IBaseModel>() {
        abstract fun showToast()
    }
}