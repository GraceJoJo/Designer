package com.jojo.design.module_discover.ui

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.config.arouter.ARouterConstants
import com.jojo.design.common_base.dagger.mvp.BaseFragment
import com.jojo.design.common_base.utils.RecyclerviewHelper
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_core.dagger2.DaggerFoundComponent
import com.jojo.design.module_core.mvp.contract.CategoryContract
import com.jojo.design.module_core.mvp.model.CategoryModel
import com.jojo.design.module_core.mvp.presenter.CategoryPresenter
import com.jojo.design.module_discover.R
import com.jojo.design.module_discover.adapter.ADA_Category
import com.jojo.design.module_discover.adapter.ADA_CategoryDetail
import com.jojo.design.module_discover.bean.CategoryBean
import com.jojo.design.module_discover.bean.ItemEntity
import com.jojo.design.module_discover.bean.TabEntity
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.fra_category_detail.*

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/23 5:55 PM
 *    desc   :
 */
class FRA_CategoryDetail : BaseFragment<CategoryPresenter, CategoryModel>(), CategoryContract.View {
    var mAdapter: ADA_CategoryDetail? = null
    override fun getContentViewLayoutId(): Int = R.layout.fra_category_detail

    override fun onFirstUserVisible() {
        var type = arguments?.getInt("type")
        //懒加载：第一次进来时请求数据，后续切换tab不再请求
        mPresenter?.getCategorieDetail((activity as ACT_CategoryDetail).categoryId, type!!)
    }

    override fun onFirstUserInvisible() {
    }

    override fun onUserVisible() {
        var type = arguments?.getInt("type")
        Log.d("tab", "type=" + type)
    }

    override fun onUserInvisible() {
    }


    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
        DaggerFoundComponent.builder().applicationComponent(BaseAppliction.mApplicationComponent).build().inject(this)
    }

    override fun startFragmentEvents() {
        val type = arguments?.getInt("type")
        Log.d("tab", "type=" + type)
//        initTestData(type)
        mAdapter = ADA_CategoryDetail(activity!!)
        rv.setPullRefreshEnabled(false)
        RecyclerviewHelper.initLayoutManagerRecyclerView(rv, mAdapter!!, LinearLayoutManager(mContext), mContext)

//        //请求分类详情
//        mPresenter?.getCategorieDetail((activity as ACT_CategoryDetail).categoryId, type!!)
    }

    /**
     * 模拟数据
     */
    private fun initTestData(type: Int?) {
        rv.layoutManager = LinearLayoutManager(mContext)
        val adapter = ADA_Category(mContext)
        rv.adapter = adapter


        var dataList = ArrayList<CategoryBean>()
        for (i in 0..40) {
            var bean = CategoryBean(i.toString(), type.toString(), "", "http://img.kaiyanapp.com/7c46ad04ff913b87915615c78d226a40.jpeg?imageMogr2/quality/60/format/jpg", "")
            dataList.add(bean)
        }
        adapter.update(dataList, true)
    }

    override fun getCategoryTabs(dataBean: TabEntity) {
    }

    override fun getCategories(dataList: List<CategoryBean>) {
    }

    override fun getCategorieDetail(dataBean: ItemEntity) {
        mAdapter?.update(dataBean?.itemList, true)
        for (i in 0 until dataBean.itemList.size) {
            if (!dataBean?.itemList[i]?.type.equals("video")) {
                Log.e("TAG", "datatype=" + dataBean?.itemList[i].data.dataType)
            } else {
                Log.e("TAG", "title=" + dataBean?.itemList[i].data.title)
            }
        }
    }


}