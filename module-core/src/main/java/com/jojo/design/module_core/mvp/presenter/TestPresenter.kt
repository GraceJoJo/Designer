package com.jojo.design.module_core.mvp.presenter

import android.util.Log
import com.jojo.design.common_base.dagger.mvp.BasePresenter
import com.jojo.design.module_core.mvp.contract.TestContract
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/4 10:07 PM
 *    desc   : Dagger2_MVP-Presenter
 */
class TestPresenter @Inject constructor() : BasePresenter<TestContract.View, TestContract.Model>(), TestContract.Presenter {
    override fun getData(text: String) {
        Log.e("TAG", "TestPresenter" + text)
        mView?.getData(mModel?.getData() + "")
        Log.e("TAG", "   mView?.getData()")
    }
}