package com.jojo.design.module_core.ui.home

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.jojo.design.common_base.dagger.mvp.BaseActivity
import com.jojo.design.common_base.dagger.mvp.BaseContract
import com.jojo.design.common_base.utils.StatusBarHelper
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_core.R
import com.jojo.design.module_core.bean.MainTabEntity
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.act_main.*

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/2 2:53 PM
 *    desc   : 主页四个Tab Activity
 */
class ACT_Home : BaseActivity<BaseContract.BasePresenter, BaseContract.BaseModel>() {
    private val mTitles = arrayOf("专题", "设计师", "逛", "发现")
    private val mTabList = ArrayList<CustomTabEntity>()
    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.drawable.icn_zhuanti, R.drawable.icn_designer, R.drawable.icn_guang, R.drawable.icn_mycenter)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.drawable.icn_zhuanti_highlight, R.drawable.icn_designer_highlight, R.drawable.icn_guang_highlight, R.drawable.icn_mycenter_highlight)
    //默认tab索引
    private var mIndex = 2
    private var mTopicFragment: TopicFragment? = null
    open var mDesignerFragment: DesignerFragment? = null
    open var mShoppingFragment: ShoppingFragment? = null
    private var mDiscorverFragment: DiscoveryFragment? = null
    override fun getContentViewLayoutId(): Int = R.layout.act_main

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        initTab()
        tabLayout.currentTab = mIndex
        switchFragment(mIndex)
    }

    override fun startEvents() {

    }

    //初始化底部菜单
    private fun initTab() {
        (0 until mTitles.size)
                .mapTo(mTabList) { MainTabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        //为Tab赋值
        tabLayout.setTabData(mTabList)
        tabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                //切换Fragment
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    /**
     * 切换Fragment
     * @param position 下标
     */
    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 //专题
            -> {
                if (mTopicFragment == null) {
                    mTopicFragment = TopicFragment.getInstance(mTitles[position])
                    transaction.add(R.id.fl_container, mTopicFragment, "special")
                } else {
                    transaction.show(mTopicFragment)
                }
                StatusBarHelper.setStatusTextColor(true, this)
            }
            1 //设计师
            -> {
                if (mDesignerFragment == null) {
                    mDesignerFragment = DesignerFragment.getInstance(mTitles[position])
                    transaction.add(R.id.fl_container, mDesignerFragment, "designer")
                } else {
                    transaction.show(mDesignerFragment)
                }
                StatusBarHelper.setStatusTextColor(false, this)
            }
            2 //逛
            -> {
                if (mShoppingFragment == null) {
                    mShoppingFragment = ShoppingFragment.getInstance(mTitles[position])
                    transaction.add(R.id.fl_container, mShoppingFragment, "mall")
                } else {
                    transaction.show(mShoppingFragment)
                }
                StatusBarHelper.setStatusTextColor(false, this)
            }

            3 //发现
            -> {
                if (mDiscorverFragment == null) {
                    mDiscorverFragment = DiscoveryFragment.getInstance(mTitles[position])
                    transaction.add(R.id.fl_container, mDiscorverFragment, "discovery")
                } else {
                    transaction.show(mDiscorverFragment)
                }
                StatusBarHelper.setStatusTextColor(false, this)
            }
        }
        mIndex = position
        tabLayout.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }


    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        if (null != mTopicFragment) {
            transaction.hide(mTopicFragment)
        }
        if (null != mDesignerFragment) {
            transaction.hide(mDesignerFragment)
        }
        if (null != mShoppingFragment) {
            transaction.hide(mShoppingFragment)
        }
        if (null != mDiscorverFragment) {
            transaction.hide(mDiscorverFragment)
        }

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (tabLayout != null) {
            outState.putInt("currTabIndex", mIndex)
        }
    }
}