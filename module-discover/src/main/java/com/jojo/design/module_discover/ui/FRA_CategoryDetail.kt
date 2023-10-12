package com.jojo.design.module_discover.ui

import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.adapter.rv.MultiItemTypeAdapter
import com.jojo.design.common_base.dagger.mvp.BaseFragment
import com.jojo.design.common_base.utils.RecyclerviewHelper
import com.jojo.design.common_base.utils.ToastUtils
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
import kotlinx.android.synthetic.main.act_category_detail.appbar
import kotlinx.android.synthetic.main.fra_category_detail.*
import kotlin.random.Random

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/23 5:55 PM
 *    desc   : 分类详情四个Tab页面
 */
class FRA_CategoryDetail : BaseFragment<CategoryPresenter, CategoryModel>(), CategoryContract.View {
    var mAdapter: ADA_CategoryDetail? = null
    var mAdapter2: ADA_Category? = null
    lateinit var layoutManager: LinearLayoutManager
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
        DaggerFoundComponent.builder().applicationComponent(BaseAppliction.mApplicationComponent)
            .build().inject(this)
    }

    override fun startFragmentEvents() {
        val type = arguments?.getInt("type")
        Log.d("tab", "type=" + type)
        initTestData(type)

//        mAdapter = ADA_CategoryDetail(activity!!)
//        rv.setPullRefreshEnabled(false)
//        RecyclerviewHelper.initLayoutManagerRecyclerView(rv, mAdapter!!,
//            LinearLayoutManager(mContext), mContext)
//        //请求分类详情
//        mPresenter?.getCategorieDetail((activity as ACT_CategoryDetail).categoryId, type!!)
    }


    /**
     * 模拟数据
     */
    private fun initTestData(type: Int?) {
        mAdapter2 = ADA_Category(activity!!)
        layoutManager = LinearLayoutManager(mContext)
        RecyclerviewHelper.initRecyclerView(rv, layoutManager, mAdapter2!!, activity!!)


        var dataList = ArrayList<CategoryBean>()
        for (i in 0..10) {
            var bean = CategoryBean(
                i.toString(),
                type.toString(),
                "",
                "http://img.kaiyanapp.com/7c46ad04ff913b87915615c78d226a40.jpeg?imageMogr2/quality/60/format/jpg",
                ""
            )
            dataList.add(bean)
        }
        mAdapter2?.update(dataList, true)

        rv.setOnRefreshListener {
            Handler().postDelayed({
                var dataList = ArrayList<CategoryBean>()
                for (i in 0..1) {
                    var bean = CategoryBean(
                        Random.nextInt().toString() + "哈哈哈",
                        type.toString(),
                        "",
                        "http://img.kaiyanapp.com/7c46ad04ff913b87915615c78d226a40.jpeg?imageMogr2/quality/60/format/jpg",
                        ""
                    )
                    dataList.add(bean)
                }
                val holderItemHeight =
                    rv.findViewHolderForAdapterPosition(1)?.itemView?.measuredHeight ?: 0
                Log.e(
                    "TTT",
                    "-----holderItemHeight:${holderItemHeight}   dataList.size:${dataList.size}"
                )
                val offsetY = holderItemHeight * dataList.size
//                //滚动recyclerview的内容部分
                rv.scrollBy(0, offsetY)

                mAdapter2?.updateForward(dataList, false)
                rv.refreshComplete(1)
                (activity as ACT_CategoryDetail).restoreNestedScrollState()

            }, 2000)

        }
        rv?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

            }
        })
        mAdapter2?.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
//                rv.restoreStatus(true, true)
                ToastUtils.makeEventToast("position:${position}", false)

//               (activity as ACT_CategoryDetail).appbar.setExpanded(false, false)
//                val holderItemHeight = rv.findViewHolderForAdapterPosition(0)?.itemView?.height ?:0
//                rv.post {
//                    rv.smoothScrollBy(0,510)
//                }

            }

            override fun onItemLongClick(
                view: View?,
                holder: RecyclerView.ViewHolder?,
                position: Int
            ): Boolean {
                return false
            }

        })
        rv.setOnLoadMoreListener {
            Handler().postDelayed({
                var dataList = ArrayList<CategoryBean>()
                for (i in 0..10) {
                    var bean = CategoryBean(
                        i.toString(),
                        type.toString(),
                        "",
                        "http://img.kaiyanapp.com/7c46ad04ff913b87915615c78d226a40.jpeg?imageMogr2/quality/60/format/jpg",
                        ""
                    )
                    dataList.add(bean)
                }
                mAdapter2?.update(dataList, false)
                rv.refreshComplete(1)
            }, 2000)
        }
    }

    override fun getCategoryTabs(dataBean: TabEntity) {
    }

    override fun getCategories(dataList: List<CategoryBean>) {
    }

    override fun getCategorieDetail(dataBean: ItemEntity) {
        mAdapter?.update(dataBean?.itemList, true)
//        for (i in 0 until dataBean.itemList.size) {
//            if (!dataBean?.itemList[i]?.type.equals("video")) {
//                Log.e("TAG", "datatype=" + dataBean?.itemList[i].data.dataType)
//            } else {
//                Log.e("TAG", "title=" + dataBean?.itemList[i].data.title)
//            }
//        }
    }


}