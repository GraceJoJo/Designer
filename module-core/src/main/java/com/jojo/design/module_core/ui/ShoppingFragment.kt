package com.jojo.design.module_core.ui

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.dagger.mvp.BaseFragment
import com.jojo.design.common_ui.lrecyclerview.recyclerview.LRecyclerViewAdapter
import com.jojo.design.common_ui.lrecyclerview.recyclerview.ProgressStyle
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_core.R
import com.jojo.design.module_core.RefreshView
import com.jojo.design.module_core.adapter.ADA_ShoppingContent
import com.jojo.design.module_core.adapter.ADA_TestFragment
import com.jojo.design.module_core.bean.CategoryEntity
import com.jojo.design.module_core.bean.ContentBean
import com.jojo.design.module_core.bean.GoodsEntity
import com.jojo.design.module_core.dagger2.DaggerCoreComponent
import com.jojo.design.module_core.mvp.contract.ShoppingContract
import com.jojo.design.module_core.mvp.model.ShoppingModel
import com.jojo.design.module_core.mvp.presenter.ShoppingPresenter
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.common_recyclcerview.*

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/7 11:34 AM
 *    desc   : 逛（shoping）
 */
class ShoppingFragment : BaseFragment<ShoppingPresenter, ShoppingModel>(), ShoppingContract.View {
    private var mTitle: String? = null

    override fun getContentViewLayoutId(): Int = R.layout.fra_shopping
    lateinit var mAdapter: ADA_ShoppingContent

    companion object {
        fun getInstance(title: String): ShoppingFragment {
            var fragment = ShoppingFragment()
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
//        var mAdapter = ADA_TestFragment(mContext)

        mAdapter = ADA_ShoppingContent(activity!!)
        val mLRecyclerViewAdapter = LRecyclerViewAdapter(mAdapter)
        lrecyclerview.setRefreshHeader(RefreshView(mContext))
        val headerView = LayoutInflater.from(mContext).inflate(R.layout.shoping_head_view, null, false)
        var recyclerview = headerView.findViewById<RecyclerView>(R.id.recyclerview)
//        mLRecyclerViewAdapter.addHeaderView(headerView)
        //设置外层列表Adapter
        lrecyclerview.adapter = mLRecyclerViewAdapter
        lrecyclerview.setHasFixedSize(true)
        lrecyclerview.layoutManager = LinearLayoutManager(mContext)
        lrecyclerview.setRefreshProgressStyle(ProgressStyle.SysProgress)
        lrecyclerview.setLoadingMoreProgressStyle(ProgressStyle.SysProgress)
        //设置头部文字颜色
        lrecyclerview.setHeaderViewColor(R.color.color_app_yellow, R.color.color_app_yellow, R.color.color_ffffff)
        //设置底部加载颜色-loading动画颜色,文字颜色,footer的背景颜色
        lrecyclerview.setFooterViewColor(R.color.color_app_yellow, R.color.color_app_yellow, R.color.color_ffffff)
        //设置底部加载文字提示
        lrecyclerview.setFooterViewHint(mContext.resources.getString(R.string.list_footer_loading), mContext.resources.getString(R.string.list_footer_end), mContext.resources.getString(R.string.list_footer_network_error))

        var data = ArrayList<String>()
        (0..50).mapTo(data) { "Item=" + it }
//        mAdapter.update(data, true)
        lrecyclerview.setOnRefreshListener {
            Handler().postDelayed({
                lrecyclerview.refreshComplete(1)
            }, 1000)
        }

        var mAdapter2 = ADA_TestFragment(mContext)
        recyclerview.adapter = mAdapter2
        recyclerview.layoutManager = LinearLayoutManager(mContext)
        mAdapter2.update(data.subList(0, 5), true)


        mPresenter?.getCategoryList()
    }

    var mCategoryList: ArrayList<CategoryEntity> = ArrayList<CategoryEntity>()
    override fun getCategoryList(dataList: List<CategoryEntity>) {
        Log.e("TAG", "getCategoryList=" + dataList.size)
        mCategoryList.addAll(dataList)
        mPresenter?.getGoodsList()
    }

    override fun getGoodsList(dataList: List<GoodsEntity>) {
        var mData = ArrayList<ContentBean>()
        Log.e("TAG", "getGoodsList=" + dataList.size)
        var categoryBean = ContentBean(1, mCategoryList, dataList)
        var goodsBean = ContentBean(2, mCategoryList, dataList)
        var normalBean = ContentBean(3, mCategoryList, dataList)
        mData.add(categoryBean)
        mData.add(goodsBean)
//        mData.add(normalBean)
        mAdapter.update(mData, true)

    }

}