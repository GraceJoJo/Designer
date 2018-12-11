package com.jojo.design.module_core.ui

import android.os.Bundle
import com.jojo.design.common_base.dagger.mvp.BaseContract
import com.jojo.design.common_base.dagger.mvp.BaseFragment
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_core.R
import com.will.weiyuekotlin.component.ApplicationComponent

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/7 11:34 AM
 *    desc   : 专题
 */
class SpecialFragment : BaseFragment<BaseContract.BasePresenter, BaseContract.BaseModel>() {
    private var mTitle: String? = null
    companion object {
        fun getInstance(title: String): SpecialFragment {
            var fragment = SpecialFragment()
            var bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }
    override fun onFirstUserVisible() {
    }

    override fun onFirstUserInvisible() {
    }

    override fun onUserVisible() {
    }

    override fun onUserInvisible() {
    }

    override fun getContentViewLayoutId(): Int = R.layout.fra_special

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {

    }

    override fun startFragmentEvents() {

    }
}