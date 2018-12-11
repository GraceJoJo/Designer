package com.jojo.design.module_core.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import com.jojo.design.common_base.dagger.mvp.BaseFragment
import com.jojo.design.common_base.dagger.mvp.BaseLazyFragment
import com.jojo.design.module_core.R.id.viewpager
import java.util.ArrayList

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/10 9:55 PM
 *    desc   :
 */
class ADA_PagerDesignerType constructor(fm: FragmentManager, fragmentList: List<BaseLazyFragment>) : FragmentStatePagerAdapter(fm) {
    var mFragmentList = ArrayList<BaseLazyFragment>()

    init {
        mFragmentList = fragmentList as ArrayList<BaseLazyFragment>
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }
}