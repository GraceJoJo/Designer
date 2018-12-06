package com.jojo.design.common_base.dagger.mvp

import com.jojo.design.common_base.bean.ErrorBean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/4 10:13 PM
 *    desc   : Dagger2_MVP 契约类-管理MVP层接口
 */
interface BaseContract {
    interface BaseView {
        fun showLoading()

        fun showDialogLoading(msg: String)

        fun dismissDialogLoading()

        fun showBusinessError(error: ErrorBean)

        fun showException(error: ErrorBean)
    }

    interface BasePresenter {

        /**
         * 绑定View
         */
        fun attachViewModel(view: BaseView,model: BaseContract.BaseModel)

        /**
         * 解除绑定
         */
        fun detachView()

        fun onDestroy()
    }

    interface BaseModel {
    }
}