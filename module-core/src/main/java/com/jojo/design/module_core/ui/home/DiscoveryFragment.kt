package com.jojo.design.module_core.ui.home

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.jojo.design.common_base.config.arouter.ARouterConfig
import com.jojo.design.common_base.config.arouter.ARouterConstants
import com.jojo.design.common_base.dagger.mvp.BaseContract
import com.jojo.design.common_base.dagger.mvp.BaseFragment
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_core.R
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.fra_discorvery.*

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/7 11:34 AM
 *    desc   : 发现
 */
class DiscoveryFragment : BaseFragment<BaseContract.BasePresenter, BaseContract.BaseModel>() {
    private var mTitle: String? = null
    companion object {
        fun getInstance(title: String): DiscoveryFragment {
            var fragment = DiscoveryFragment()
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

    override fun getContentViewLayoutId(): Int = R.layout.fra_discorvery

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {

    }

    override fun startFragmentEvents() {
        tv.setOnClickListener {
            ARouter.getInstance().build(ARouterConfig.ACT_Category)
                    .navigation()
        }
    }
}