package com.jojo.design.module_core.adapter

import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import androidx.cardview.widget.CardView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.alibaba.android.arouter.launcher.ARouter
import com.jojo.design.common_base.BaseAppliction.Companion.context
import com.jojo.design.common_base.config.arouter.ARouterConfig
import com.jojo.design.common_base.config.arouter.ARouterConstants
import com.jojo.design.common_base.utils.glide.GlideUtils
import com.jojo.design.module_core.R
import com.jojo.design.module_core.bean.TopicBean
import com.jojo.design.module_core.widgets.cardview.ICardAdapter
import java.util.ArrayList

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/17 4:58 PM
 *    desc   : 专题页面卡片适配器
 */
class ADA_TopicPager constructor(context: Context, datas: List<TopicBean>) : PagerAdapter(), ICardAdapter {

    private var mViewList = ArrayList<CardView?>()
    private var mDatas = ArrayList<TopicBean>()
    private var mBaseElevation: Float = 0.toFloat()
    private var mContext: Context? = null

    init {
        mContext = context
        mDatas = datas as ArrayList<TopicBean>
        mViewList.clear()
        for (i in datas.indices) {
            mViewList.add(null)
        }
    }

    fun notifyChanged(datas: List<TopicBean>) {
        mDatas.clear()
        mViewList.clear()
        for (i in datas.indices) {
            mViewList.add(null)
            mDatas.add(datas[i])
        }
        notifyDataSetChanged()
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
        mViewList[position] = null
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
                .inflate(R.layout.item_topic_card, container, false)
        container.addView(view)
        val cardView = view.findViewById<CardView>(R.id.cardView)

        //专题bean
        val bean = mDatas[position]
        //专题点击处理
        cardView.setOnClickListener {
            if (!TextUtils.isEmpty(bean.url)) {
                ARouter.getInstance().build(ARouterConfig.ACT_WEBVIEW)
                        .withString(ARouterConstants.WEB_TITLE, bean.name)
                        .withString(ARouterConstants.WEB_URL, bean.url)
                        .navigation()
            }
        }

        val ivCard = view.findViewById<ImageView>(R.id.iv_card)
        GlideUtils.loadImage(bean.image, ivCard, 0)
        if (mBaseElevation == 0f) {
            mBaseElevation = cardView.cardElevation
        }
        cardView.maxCardElevation = mBaseElevation * getMaxElevationFactor()
        mViewList[position] = cardView
        return view
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj
    override fun getCount(): Int = mViewList.size
    override fun getBaseElevation(): Float = mBaseElevation

    override fun getCardViewAt(position: Int): CardView = mViewList.get(position)!!

    override fun getMaxElevationFactor(): Int = 10
}