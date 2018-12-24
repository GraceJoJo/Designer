package com.jojo.design.module_core.ui.home

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.jojo.design.common_base.dagger.mvp.BaseContract
import com.jojo.design.common_base.dagger.mvp.BaseFragment
import com.jojo.design.common_ui.lrecyclerview.interfaces.OnItemClickListener
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_core.R
import com.jojo.design.module_core.adapter.ADA_DesignerTypeList
import com.jojo.design.module_core.bean.TagCategoryEntity
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.fra_designertype_list.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/10 3:45 PM
 *    desc   : Designer-设计分类 行业、风格等
 */
class FRA_DesignerTypeList : BaseFragment<BaseContract.BasePresenter, BaseContract.BaseModel>() {
    var mAdapter: ADA_DesignerTypeList? = null
    var mSelectedPosition = 0
    override fun getContentViewLayoutId(): Int = R.layout.fra_designertype_list


    companion object {
        fun newInstance(type: String): FRA_DesignerTypeList {
            val pagerFragment = FRA_DesignerTypeList()
            val bundle = Bundle()
            bundle.putString("type", type)
            pagerFragment.arguments = bundle
            return pagerFragment
        }

    }

    /**
     * 接收ShopingFragment请求的设计师分类列表数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun refreshData(dataList: List<TagCategoryEntity>) {
        Log.e("sss", "dataList[mSelectedPosition].tags=" + mSelectedPosition)
        mAdapter?.update(dataList[mSelectedPosition].tags, true)
        //解决进入页面时列表高度显示不全的问题
        Handler().postDelayed({
            (activity as MainActivity).mDesignerFragment?.resetViewPagerHeight(0)
        }, 500)

    }

    override fun isBindEventBus(isBind: Boolean): Boolean {
        return super.isBindEventBus(true)
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

    }

    override fun startFragmentEvents() {
        mSelectedPosition = FragmentPagerItem.getPosition(arguments)

        mAdapter = ADA_DesignerTypeList(mContext)
        gridview.adapter = mAdapter
        gridview.isFocusableInTouchMode = false
    }
}