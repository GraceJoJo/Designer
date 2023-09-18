package com.jojo.design.module_core.ui.home

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.jojo.design.common_base.dagger.mvp.BaseContract
import com.jojo.design.common_base.dagger.mvp.BaseFragment
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_core.R
import com.jojo.design.module_core.adapter.TextTagsAdapter
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.fra_discorvery.*

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/7 11:34 AM
 *    desc   : 发现(供拓展的学习模块)
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

        initCloudView()

    }

    private fun initCloudView() {
        tag_cloud.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_ffffff));
        var data = arrayListOf<String>("开眼视频", "Kotlin", "组件化", "MVP", "Retrofit", "Rxjava", "开眼视频", "好好学习", "进入开眼视频", "有趣的内容", "组件化", "Kotlin", "Dagger2", "开眼视频", "Room数据库", "Retrofit", "开眼视频", "沉浸式状态栏", "5.0新特性", "Rxjava", "烟火里的尘埃", "进入开眼视频", "猪年快乐", "加油努力", "进入开眼视频", "猪事顺利", "ARouter")
        var tagsAdapter = TextTagsAdapter(data)//new String[20]-*arrayOfNulls(20)
        tag_cloud.setAdapter(tagsAdapter)
    }

}