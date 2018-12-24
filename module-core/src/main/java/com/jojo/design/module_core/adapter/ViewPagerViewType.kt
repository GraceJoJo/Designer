package com.jojo.design.module_core.adapter

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.jojo.design.common_base.adapter.rv.ItemViewDelegate
import com.jojo.design.common_base.adapter.rv.ViewHolder
import com.jojo.design.common_ui.view.CustomViewPager
import com.jojo.design.module_core.R
import com.jojo.design.module_core.bean.ContentBean
import com.jojo.design.module_core.ui.home.HandpickedFragment
import com.jojo.design.module_core.ui.home.PersonLikeFragment
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/12 10:02 PM
 *    desc   :
 */
class ViewPagerViewType constructor(context: FragmentActivity) : ItemViewDelegate<ContentBean> {
    var activity: FragmentActivity? = null
    var viewpager: CustomViewPager? = null

    init {
        activity = context
    }

    override fun getItemViewLayoutId(): Int = R.layout.item_viewpager

    override fun isForViewType(item: ContentBean, position: Int): Boolean {
        return item.type == 3
    }

    override fun convert(holder: ViewHolder, bean: ContentBean, position: Int) {
        viewpager = holder.getView<CustomViewPager>(R.id.viewpager)
        var tablayout = holder.getView<SmartTabLayout>(R.id.tablayout)
        createFragment(viewpager!!, tablayout,bean)
    }

    /**
     * 创建Fragment和ViewPager
     */
    private fun createFragment(viewpager: CustomViewPager, tablayout: SmartTabLayout, bean: ContentBean) {
        var dataList = ArrayList<String>()
        dataList.add("精选")
        dataList.add("大家喜欢")
        val pages = FragmentPagerItems(activity)
        for (i in 0 until dataList.size) {
            if (i == 0) {
                pages.add(FragmentPagerItem.of(dataList[i], HandpickedFragment::class.java!!))
            } else {
                pages.add(FragmentPagerItem.of(dataList[i], PersonLikeFragment::class.java!!))
            }

        }
        val adapter = FragmentPagerItemAdapter(activity?.supportFragmentManager,
                pages)
        viewpager.adapter = adapter!!
        tablayout.setViewPager(viewpager)
        //        susTablayout.setViewPager(viewpager)
    }
}