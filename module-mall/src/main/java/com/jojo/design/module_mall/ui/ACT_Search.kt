package com.jojo.design.module_mall.ui

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.config.arouter.ARouterConfig
import com.jojo.design.common_base.dagger.mvp.BaseActivity
import com.jojo.design.common_base.utils.RecyclerviewHelper
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_core.R
import com.jojo.design.module_core.dagger2.DaggerMallComponent
import com.jojo.design.module_mall.bean.SearchHistoryBean
import com.jojo.design.module_mall.adapter.ADA_SearchHistory
import com.jojo.design.module_mall.bean.RecordsEntity
import com.jojo.design.module_mall.mvp.SearchContract
import com.jojo.design.module_mall.mvp.presenter.SearchModel
import com.jojo.design.module_mall.mvp.presenter.SearchPresenter
import com.smart.novel.adapter.ADA_HotSearchTag
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.act_search.*

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/29 10:08 AM
 *    desc   : 商品搜索页面
 */
@Route(path = ARouterConfig.ACT_SEARCH)
class ACT_Search : BaseActivity<SearchPresenter, SearchModel>(), SearchContract.View {

    override fun getContentViewLayoutId(): Int = R.layout.act_search

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
        DaggerMallComponent.builder().applicationComponent(BaseAppliction.mApplicationComponent).build().inject(this)
    }

    override fun startEvents() {
        var data = ArrayList<SearchHistoryBean>()
        (0..10).mapTo(data) { SearchHistoryBean(it.toLong(), "item=" + it) }


        var mAdapter = ADA_SearchHistory(mContext)
        RecyclerviewHelper.initNormalRecyclerView(rvHistory, mAdapter, object : LinearLayoutManager(mContext) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        })
        mAdapter.update(data, true)

        mPresenter?.getHotList()
//        mPresenter?.getSearchGoods("2", "", 0)
    }

    override fun getHotList(dataList: List<String>) {
        Log.e("TAG", "dataList=" + dataList.size)
        var mHotSearchAdapter = ADA_HotSearchTag(dataList)
        taglayout.adapter = mHotSearchAdapter
    }

    override fun getSearchGoods(dataBean: RecordsEntity) {
        Log.e("TAG", "dataBean=" + dataBean.size)
    }
}