package com.jojo.design.common_base.dagger.mvp

import com.jojo.design.common_base.net.RxManager

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/4 9:49 PM
 *    desc   : Dagger2_MVP -BasePresenter
 */
open abstract class BasePresenter<V : BaseContract.BaseView, M : BaseContract.BaseModel> : BaseContract.BasePresenter {
    var mView: V? = null
    var mModel: M? = null
    var rxManager: RxManager? = RxManager()

    override fun attachViewModel(view: BaseContract.BaseView, model: BaseContract.BaseModel) {
        mView = view as V
        mModel = model as M
    }

    override fun detachView() {
        mView?.let { mView = null }
        mModel?.let { mModel = null }
    }

    override fun onDestroy() {
        rxManager!!.clear()
        rxManager = null
    }

}