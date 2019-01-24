package com.jojo.design.module_discover.ui

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.adapter.rv.MultiItemTypeAdapter
import com.jojo.design.common_base.config.arouter.ARouterConfig
import com.jojo.design.common_base.config.arouter.ARouterConstants
import com.jojo.design.common_base.dagger.mvp.BaseActivity
import com.jojo.design.common_base.utils.StatusBarHelper
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_core.dagger2.DaggerFoundComponent
import com.jojo.design.module_core.mvp.contract.CategoryContract
import com.jojo.design.module_core.mvp.model.CategoryModel
import com.jojo.design.module_core.mvp.presenter.CategoryPresenter
import com.jojo.design.module_discover.R
import com.jojo.design.module_discover.adapter.ADA_Category
import com.jojo.design.module_discover.bean.CategoryBean
import com.jojo.design.module_discover.bean.ItemEntity
import com.jojo.design.module_discover.bean.TabEntity
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.act_category.*
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

/**
 * 开眼视频分类页面
 */
@Route(path = ARouterConfig.ACT_Category)
class ACT_Category : BaseActivity<CategoryPresenter, CategoryModel>(), CategoryContract.View {
    var mAdapter: ADA_Category? = null
    override fun getContentViewLayoutId(): Int = R.layout.act_category

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
        DaggerFoundComponent.builder().applicationComponent(BaseAppliction.mApplicationComponent).build().inject(this)
    }

    override fun startEvents() {
        StatusBarHelper.setStatusTextColor(true, this)
        setHeaderTitle("全部分类")
        mPresenter?.getCategories()

        mAdapter = ADA_Category(mContext)
        rv_category.layoutManager = GridLayoutManager(mContext, 2)
        rv_category.adapter = mAdapter

        // //设置item之间的间距
        rv_category.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView?) {
                if (itemPosition > 1) outRect.top = 5

                if ((itemPosition + 1) % 2 == 0) outRect.left = 5
            }
        })
        OverScrollDecoratorHelper.setUpOverScroll(rv_category, OverScrollDecoratorHelper.ORIENTATION_VERTICAL)

        initListener()
    }

    private fun initListener() {
        mAdapter?.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
                val bean = mAdapter!!.dataList[position]
                ARouter.getInstance().build(ARouterConfig.ACT_CategoryDetail)
                        .withString(ARouterConstants.CATEGORY_HEAD_IMAGE, bean.headerImage)
                        .withString(ARouterConstants.CATEGORY_ID, bean.id)
                        .withString(ARouterConstants.CATEGORY_NAME, bean.name)
                        .withSerializable(ARouterConstants.CATEGORY_BEAN, bean)
                        .navigation()
            }

            override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
                return false;
            }

        })
    }

    override fun getCategoryTabs(dataBean: TabEntity) {
    }

    override fun getCategories(dataList: List<CategoryBean>) {
        Log.e("TAG", "dataList=" + dataList.size)
        mAdapter?.update(dataList, true)
    }

    override fun getCategorieDetail(dataBean: ItemEntity) {
    }

}
