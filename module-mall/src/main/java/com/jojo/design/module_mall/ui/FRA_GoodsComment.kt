package com.jojo.design.module_mall.ui

import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.dagger.mvp.BaseFragment
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_mall.R
import com.jojo.design.module_mall.adapter.ADA_GoodsComment
import com.jojo.design.module_mall.adapter.ADA_GoodsRecommend
import com.jojo.design.module_mall.bean.CommentBean
import com.jojo.design.module_mall.bean.GoodsContentBean
import com.jojo.design.module_mall.bean.GoodsDesBean
import com.jojo.design.module_mall.bean.RevelentBean
import com.jojo.design.module_mall.dagger2.DaggerMallComponent
import com.jojo.design.module_mall.mvp.contract.GoodsContract
import com.jojo.design.module_mall.mvp.model.GoodsModel
import com.jojo.design.module_mall.mvp.presenter.GoodsPresenter
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.fra_goods_comment.*

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/15 4:03 PM
 *    desc   : 商品详情-评价
 */
class FRA_GoodsComment : BaseFragment<GoodsPresenter, GoodsModel>(), GoodsContract.View {
    var mAdapter: ADA_GoodsRecommend? = null
    var mAdapterComment: ADA_GoodsComment? = null
    override fun getContentViewLayoutId(): Int = R.layout.fra_goods_comment

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
        DaggerMallComponent.builder().applicationComponent(BaseAppliction.mApplicationComponent).build().inject(this)
    }

    override fun startFragmentEvents() {
        mPresenter?.getGoodsCommentList((activity as ACT_GoodsDetail).productId!!, 0)
        mPresenter?.getRevelentGoodsList((activity as ACT_GoodsDetail).productId!!)

        mAdapter = ADA_GoodsRecommend(mContext)
        gv_recomment.adapter = mAdapter

        mAdapterComment = ADA_GoodsComment(mContext)
        lv_comment.adapter = mAdapterComment
    }


    override fun getGoodsCommentList(dataList: List<CommentBean>) {
        tv_comment_num.text = "所有" + dataList.size + "条评论"
        mAdapterComment?.update(dataList, true)
    }

    override fun getRevelentGoodsList(dataBean: RevelentBean) {
        mAdapter?.update(dataBean?.revelentList, true)
    }

    override fun getGoodsContent(dataBean: GoodsContentBean) {
    }

    override fun getGoodsDescription(dataList: List<GoodsDesBean>) {
    }

}