package com.jojo.design.module_core.ui

import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jojo.design.common_base.bean.ErrorBean
import com.jojo.design.common_base.mvvm.BaseActivity
import com.jojo.design.common_base.mvvm.TestContract
import com.jojo.design.common_base.mvvm.TestModel
import com.jojo.design.common_base.mvvm.TestPresenter
import com.jojo.design.common_base.utils.ToastUtils
import com.jojo.design.module_core.R
import com.jojo.design.module_core.databinding.ActivitySearchBinding
import com.smart.novel.util.bindView

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/11/29 3:07 PM
 *    desc   : 普通泛型MVP
 */
@Route(path = "/core/search")
class TestMVPNormalActivity : BaseActivity<TestPresenter, TestModel, ActivitySearchBinding>(), TestContract.View {
    private val tv_txt by bindView<TextView>(R.id.tv_txt)
    override fun getContentViewLayoutID(): Int = R.layout.activity_search

    override fun startEvents() {
        mMvpPresenter?.showToast()
//        mMultipleStatusView?.showLoading()
//        Handler().postDelayed({
//            mMultipleStatusView?.showContent()
//        }, 2000)

        var bean = ErrorBean()
        bean.msg = "基础MVP-Activity中的DataBinding测试"
        viewDataBinding?.bean = bean

        tv_txt.text = "普通MVP-测试ButterKnife成功,点我跳转"

        tv_txt.setOnClickListener({
            ARouter.getInstance().build("/base/act_testdagger").navigation()
        })

        var fragment = TestFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_container,fragment)
        transaction.commitNowAllowingStateLoss()
    }
    override fun showToast(text: String) {
        ToastUtils.makeShortToast(text)
    }
}