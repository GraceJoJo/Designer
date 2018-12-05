package com.jojo.design.module_core.mvp.contract

import com.jojo.design.common_base.dagger.mvp.BaseContract

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/4 10:09 PM
 *    desc   : Dagger2_MVP-契约类：管理MVP
 */
interface TestContract {
    interface View : BaseContract.BaseView {
        fun getData(result: String)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getData(text: String)
    }

    interface Model : BaseContract.BaseModel {
        fun getData():String
    }
}