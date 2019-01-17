package com.jojo.design.module_core.ui.home

import android.os.Bundle
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.dagger.mvp.BaseFragment
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_core.R
import com.jojo.design.module_core.adapter.ADA_TopicPager
import com.jojo.design.module_core.bean.TopicBean
import com.jojo.design.module_core.bean.TopicDetailEntity
import com.jojo.design.module_core.dagger2.DaggerCoreComponent
import com.jojo.design.module_core.mvp.contract.TopicContract
import com.jojo.design.module_core.mvp.model.TopicModel
import com.jojo.design.module_core.mvp.presenter.TopicPresenter
import com.jojo.design.module_core.widgets.cardview.ShadowTransformer
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.fra_topic.*
import java.util.ArrayList

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/7 11:34 AM
 *    desc   : 专题
 */
class TopicFragment : BaseFragment<TopicPresenter, TopicModel>(), TopicContract.View {
    private var mTitle: String? = null
    private val mDatas = ArrayList<TopicBean>()
    private var mCardAdapter: ADA_TopicPager? = null
    private var mCardShadowTransformer: ShadowTransformer? = null
    override fun getContentViewLayoutId(): Int = R.layout.fra_topic

    companion object {
        fun getInstance(title: String): TopicFragment {
            var fragment = TopicFragment()
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

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
        DaggerCoreComponent.builder().applicationComponent(BaseAppliction.mApplicationComponent).build().inject(this)
    }

    override fun startFragmentEvents() {
        mPresenter?.getTopics("131")
        mPresenter?.getTopicDetail("5192")

        mCardAdapter = ADA_TopicPager(mContext, mDatas)
        mCardShadowTransformer = ShadowTransformer(vp_card, mCardAdapter!!)
        mCardShadowTransformer?.enableScaling(true)
        vp_card.adapter = mCardAdapter
        vp_card.setPageTransformer(false, mCardShadowTransformer)
        vp_card.offscreenPageLimit = 3
    }

    override fun getTopics(dataList: List<TopicBean>) {
        mCardAdapter?.notifyChanged(dataList)
        //需重新设置，卡片缩放效果才能起作用
        mCardShadowTransformer?.enableScaling(true)
    }

    override fun getTopicDetail(dataBean: TopicDetailEntity) {
    }
}