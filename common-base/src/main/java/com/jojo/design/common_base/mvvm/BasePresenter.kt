package com.jojo.design.common_base.mvvm

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/3 2:58 PM
 *    desc   : BasePresenter
 */
open abstract class BasePresenter<V : IBaseView, M : IBaseModel> {
    lateinit var mModel: M
    lateinit var mView: V
    var rxManager: RxManager? = RxManager()

    fun setVM(v: V, m: M) {
        this.mView = v
        this.mModel = m
    }

    fun onDestroy() {
        rxManager!!.clear()
        rxManager = null
    }
}