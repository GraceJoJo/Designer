package com.jojo.design.module_discover.ui

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import com.google.android.material.appbar.AppBarLayout
import androidx.viewpager.widget.ViewPager
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.config.arouter.ARouterConfig
import com.jojo.design.common_base.config.arouter.ARouterConstants
import com.jojo.design.common_base.dagger.mvp.BaseActivity
import com.jojo.design.common_base.utils.StatusBarHelper
import com.jojo.design.common_base.utils.ToastUtils
import com.jojo.design.common_base.utils.glide.GlideUtils
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_core.dagger2.DaggerFoundComponent
import com.jojo.design.module_core.mvp.contract.CategoryContract
import com.jojo.design.module_core.mvp.model.CategoryModel
import com.jojo.design.module_core.mvp.presenter.CategoryPresenter
import com.jojo.design.module_discover.R
import com.jojo.design.module_discover.bean.CategoryBean
import com.jojo.design.module_discover.bean.ItemEntity
import com.jojo.design.module_discover.bean.TabEntity
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.act_category_detail.*

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/23 4:58 PM
 *    desc   : 开眼视频分类详情页面（5.0新特性CoordinatorLayout +AppBarLayout效果）
 */
@Route(path = ARouterConfig.ACT_CategoryDetail)
class ACT_CategoryDetail : BaseActivity<CategoryPresenter, CategoryModel>(), CategoryContract.View {
    var categoryId = ""
    var categoryBean: CategoryBean? = null

    private enum class AppBarState {
        EXPANDED,
        COLLAPSED,
        MIDDLE
    }

    override fun getContentViewLayoutId(): Int = R.layout.act_category_detail

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
        DaggerFoundComponent.builder().applicationComponent(BaseAppliction.mApplicationComponent)
            .build().inject(this)
    }

    override fun startEvents() {
        categoryId = intent.extras?.getString(ARouterConstants.CATEGORY_ID).toString()
        categoryBean =
            intent.extras?.getSerializable(ARouterConstants.CATEGORY_BEAN) as CategoryBean?
        intent.extras?.getString(ARouterConstants.CATEGORY_HEAD_IMAGE)
            ?.let { GlideUtils.loadNormalImage(it, iv_headImg, 0) }
        tv_name.text = categoryBean?.name ?: "标题"
        tv_des.spacing = 10f
        tv_des.setText(
            categoryBean?.description ?: "这是一个描述信息",
            TextView.BufferType.SPANNABLE
        )
        mPresenter?.getCategoryTabs(categoryId)

        initToorbar()

        //mock 数据
        createFragment(listOf(), viewpager, tablayout)
        initListener()
    }

    private var mState: AppBarState? = null
    private var scrollState = 0
    private var originHeight = 0
    private var originHeight2 = 0
    private fun initListener() {
        originHeight = toolbar_layout.layoutParams.height
        originHeight2 = header_layout.layoutParams.height
        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            Log.e("JOJO", "-----verticalOffset:${verticalOffset} mState:${mState}")
            if (verticalOffset == 0) {
                if (mState != AppBarState.EXPANDED) {
                    mState = AppBarState.EXPANDED//修改状态标记为展开

                    titleBlackMode(true)
                }
            } else if (Math.abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                if (mState != AppBarState.COLLAPSED) {
                    mState = AppBarState.COLLAPSED//修改状态标记为折叠

                    if (scrollState == 0) {
                        header_layout.layoutParams.height = 0
                        scrollState = 1
                    } else {
                        titleBlackMode(true)
                    }
                }
            } else {
                if (mState != AppBarState.MIDDLE) {
                    if (mState == AppBarState.COLLAPSED) {

                        titleBlackMode(false)
                    }
                    mState = AppBarState.MIDDLE//修改状态标记为中间

                }
            }
        })
        tv.setOnClickListener {
            ToastUtils.makeEventToast("触发appBar恢复", false)
//            toolbar_layout.layoutParams.height = originHeight
            if (scrollState == 1) {
                header_layout.layoutParams.height = originHeight2
                (toolbar_layout.layoutParams as AppBarLayout.LayoutParams).scrollFlags =
                    SCROLL_FLAG_SCROLL or SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                appbar.setExpanded(false, false)
                toolbar_layout.requestLayout()
                scrollState = 2
            }
        }
    }

    fun restoreNestedScrollState() {
        if (scrollState == 1) {
            header_layout.layoutParams.height = originHeight2
            (toolbar_layout.layoutParams as AppBarLayout.LayoutParams).scrollFlags =
                SCROLL_FLAG_SCROLL or SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
            appbar.setExpanded(false, false)
            toolbar_layout.requestLayout()
            scrollState = 2
        }
    }

    fun titleBlackMode(isDark: Boolean) {
        if (isDark) {
            tv_title.visibility = View.VISIBLE
            toolbar.setNavigationIcon(R.drawable.ic_back_arrow_black)
//            view_title_divider.visibility = View.VISIBLE
            StatusBarHelper.setStatusTextColor(true, this)
        } else {
            tv_title.visibility = View.GONE
//            view_title_divider.visibility = View.GONE
            toolbar.setNavigationIcon(R.drawable.ic_back_arrow_white)
            StatusBarHelper.setStatusTextColor(false, this)
        }
    }

    /**
     * 设置标题栏 返回icon和标题
     */
    private fun initToorbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow_white)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        tv_title.text = intent.extras?.getString(ARouterConstants.CATEGORY_NAME) ?: "Title"
    }


    override fun getCategoryTabs(dataBean: TabEntity) {
        createFragment(dataBean.tabInfo.tabList, viewpager, tablayout)
    }

    override fun getCategories(dataList: List<CategoryBean>) {
    }

    override fun getCategorieDetail(dataBean: ItemEntity) {
    }

    /**
     * 创建Fragment,关联ViewPager和Fragment
     */
    private fun createFragment(
        tabList: List<TabEntity.TabInfoEntity.TabBean>,
        viewpager: ViewPager,
        tablayout: SmartTabLayout
    ) {
        var dataList = ArrayList<String>()
        dataList.add("首页")
        dataList.add("全部")
        dataList.add("作者")
        dataList.add("专辑")
        val pages = FragmentPagerItems(this)
        (0 until dataList.size).mapTo(pages) {
            val arguments = Bundle()
            arguments.putInt("type", it)
            FragmentPagerItem.of(dataList[it], FRA_CategoryDetail::class.java, arguments)
        }
        //设置预加载Fragment的数量为tabList.size（首次进入时，viewpgaer的每个Fragment都会走startFragmentEvents，然后每次滑动切换都走onUserVisible）
        viewpager.offscreenPageLimit = dataList.size
        val adapter = FragmentPagerItemAdapter(supportFragmentManager, pages)
        viewpager.adapter = adapter
        tablayout.setViewPager(viewpager)
    }
}