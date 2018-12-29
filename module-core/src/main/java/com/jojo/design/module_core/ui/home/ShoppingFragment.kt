package com.jojo.design.module_core.ui.home

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.config.arouter.ARouterConfig
import com.jojo.design.common_base.dagger.mvp.BaseFragment
import com.jojo.design.common_base.utils.ScreenUtil
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_core.R
import com.jojo.design.module_core.adapter.ADA_ShoppingContent
import com.jojo.design.module_core.bean.*
import com.jojo.design.module_core.dagger2.DaggerCoreComponent
import com.jojo.design.module_core.mvp.contract.ShoppingContract
import com.jojo.design.module_core.mvp.model.ShoppingModel
import com.jojo.design.module_core.mvp.presenter.ShoppingPresenter
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.common_search_title.*
import kotlinx.android.synthetic.main.fra_shopping_new.*

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/7 11:34 AM
 *    desc   : 逛（shoping）-采用NestedScrollView嵌套TabLayout+ViewPager+Fragment+RecyclerView 复杂嵌套滑动冲突解决
 */
class ShoppingFragment : BaseFragment<ShoppingPresenter, ShoppingModel>(), ShoppingContract.View {

    private var mTitle: String? = null
    open lateinit var mHeaderAdapter: ADA_ShoppingContent
    var mCategoryList: ArrayList<CategoryEntity> = ArrayList<CategoryEntity>()
    override fun getContentViewLayoutId(): Int = R.layout.fra_shopping_new

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
        //获取商品分类
        mPresenter?.getCategoryList()

        mHeaderAdapter = ADA_ShoppingContent(activity!!)
        recyclerview.layoutManager = object : LinearLayoutManager(mContext) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        recyclerview.adapter = mHeaderAdapter


        ll_title.post {
            dealWithViewPager()
        }

//        createFragment(vp_shoping, tablayout)
        initListener()
    }

    var toolBarPositionY = 0
    private fun initListener() {
        scrollView.setOnScrollChangeListener(object : NestedScrollView.OnScrollChangeListener {
            internal var lastScrollY = 0

            override fun onScrollChange(v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
                var scrollY = scrollY
                val location = IntArray(2)
                rl_tablayout.getLocationOnScreen(location)
                val yPosition = location[1] //rl_tablayout顶部距离屏幕顶部的距离（Y方向的坐标）
                Log.e("scrollView", "滑动的TabLayout的位置：yPosition=" + yPosition + "固定顶部标题栏toolBarPositionY=" + toolBarPositionY)
                //直接通过yPosition < toolBarPositionY判断是否拦截子View，下滑时会顿一下，滑动距离有偏差
                if (yPosition < toolBarPositionY) {
                    rl_sus_tab.visibility = View.VISIBLE
//                    scrollView.setNeedScroll(false) //NetsedScrollView不拦截子View，让子View消费事件，处理滑动
                } else {
                    rl_sus_tab.visibility = View.GONE
//                    scrollView.setNeedScroll(true)
                }
                //解决（原因暂未明）：当上滑yPosition <toolBarPositionY时，设置scrollView.setNeedScroll(false)没有即时生效，NetsedScrollView还继续滑动了一段距离（从toolBarPositionY变到了73,子View才响应滑动事件）
                if (yPosition + (toolBarPositionY - 73) <= toolBarPositionY) {
                    scrollView.setNeedScroll(false)
                } else {
                    scrollView.setNeedScroll(true)
                }
            }
        })

        et_search.setOnClickListener {
            ARouter.getInstance().build(ARouterConfig.ACT_SEARCH).navigation()
        }
    }

    private fun dealWithViewPager() {
        toolBarPositionY = ll_title.height
        val params = vp_shoping.layoutParams
        params.height = ScreenUtil.getScreenHeight(activity as Activity) - toolBarPositionY - rl_tablayout.height
        vp_shoping.layoutParams = params
        Log.e("TAG", "viewpager=" + vp_shoping.layoutParams.height)
    }

    /**
     * 创建Fragment和ViewPager
     */
    private fun createFragment(viewpager: ViewPager, tablayout: SmartTabLayout) {
        var dataList = ArrayList<String>()
        dataList.add("精选")
        dataList.add("大家喜欢")
        val pages = FragmentPagerItems(activity)
        for (i in 0 until dataList.size) {
            if (i == 0) {
                pages.add(FragmentPagerItem.of(dataList[i], HandpickedFragment::class.java!!))
            } else {
                pages.add(FragmentPagerItem.of(dataList[i], AllFavorFragment::class.java!!))
            }

        }
        val adapter = FragmentPagerItemAdapter((activity as FragmentActivity)?.supportFragmentManager,
                pages)
        viewpager.adapter = adapter!!
        tablayout.setViewPager(viewpager)
        sus_tablayout.setViewPager(viewpager)
    }


    override fun getCategoryList(dataList: List<CategoryEntity>) {
        mCategoryList.addAll(dataList)
        //获取商品列表
        mPresenter?.getGoodsList()
    }

    override fun getGoodsList(dataList: List<GoodsEntity>) {
        var mData = ArrayList<ContentBean>()
        var categoryBean = ContentBean(1, mCategoryList, dataList)
        var goodsBean = ContentBean(2, mCategoryList, dataList)
//        var viewPagerBean = ContentBean(3, mCategoryList, dataList)
        mData.add(categoryBean)
        mData.add(goodsBean)
//        mData.add(viewPagerBean)
        mHeaderAdapter.update(mData, true)

        //创建精选+大家喜欢的Tab+ViewPager
        createFragment(vp_shoping, tablayout)
    }

    override fun getHandPickedGoods(bean: RecordsEntity) {
    }

    override fun getPersonLike(dataList: List<AllfaverEntity>) {
    }

}