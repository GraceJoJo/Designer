package com.jojo.design.module_core.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.dagger.mvp.BaseFragment
import com.jojo.design.common_base.utils.GlideUtils
import com.jojo.design.common_base.utils.StatusBarHelper
import com.jojo.design.common_ui.view.CustomViewPager
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_core.R
import com.jojo.design.module_core.bean.DesignerEntity
import com.jojo.design.module_core.bean.TagCategoryEntity
import com.jojo.design.module_core.dagger2.DaggerCoreComponent
import com.jojo.design.module_core.mvp.contract.DesignerContract
import com.jojo.design.module_core.mvp.model.DesignerModel
import com.jojo.design.module_core.mvp.presenter.DesignerPresenter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.common_sus_tab.*
import kotlinx.android.synthetic.main.common_tab.*
import kotlinx.android.synthetic.main.fra_designer.*
import org.greenrobot.eventbus.EventBus

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/7 11:34 AM
 *    desc   : 设计师
 */
class DesignerFragment : BaseFragment<DesignerPresenter, DesignerModel>(), DesignerContract.View {
    private var mTitle: String? = null
    override fun getContentViewLayoutId(): Int = R.layout.fra_designer

    companion object {
        fun getInstance(title: String): DesignerFragment {
            var fragment = DesignerFragment()
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
        mPresenter?.getRecommendDesigner()
        mPresenter?.getDesignerTypeList()
        initListener()
    }

    private fun initListener() {
        fade_status_bar.layoutParams.height = StatusBarHelper.getStatusBarHeight(mContext)
        dropScrollView.setOnScrollViewListener { scrollX, scrollY, oldx, oldY ->
            var distanceScrollY = rl_head.height + tab.height - sus_tab.height
            if (scrollY >= distanceScrollY) {
                StatusBarHelper.setStatusTextColor(true, activity!!)
                tab.visibility = View.INVISIBLE
                sus_tab.visibility = View.VISIBLE
            } else {
                StatusBarHelper.setStatusTextColor(false, activity!!)
                tab.visibility = View.VISIBLE
                sus_tab.visibility = View.INVISIBLE

            }
        }
    }

    /**
     * 根据标签数量动态创建Fragment
     */
    private fun createFragmentByTags(dataList: List<TagCategoryEntity>) {
        val pages = FragmentPagerItems(mContext)
        (0 until dataList.size).mapTo(pages) { FragmentPagerItem.of(dataList[it].name, FRA_DesignerTypeList::class.java!!) }
        val adapter = FragmentPagerItemAdapter(activity?.supportFragmentManager,
                pages)
        viewpager.adapter = adapter
        tablayout.setViewPager(viewpager)
        susTablayout.setViewPager(viewpager)
    }

    override fun getDesignerTypeList(dataList: List<TagCategoryEntity>) {
        createFragmentByTags(dataList)
        EventBus.getDefault().post(dataList)
    }

    override fun getRecommendDesigner(topDesigner: DesignerEntity) {
        tv_user_nike.text = topDesigner.userNick
        tv_op_tag.text = topDesigner.opTag
        tv_shop_name.text = topDesigner.shopName
        tv_tags.text = topDesigner.tags[0].name + "   " + topDesigner.tags[1].name
        GlideUtils.loadImage(topDesigner.banner, iv_head_cover, 0)

    }

    /**
     * 重新设置viewPager高度
     *
     * @param position
     */
    fun resetViewPagerHeight(position: Int) {
        var child = viewpager.getChildAt(position)
        if (child != null) {
            var params = viewpager.layoutParams
            params.height = viewpager.map[position]!!
            viewpager.layoutParams = params
        }
    }
}