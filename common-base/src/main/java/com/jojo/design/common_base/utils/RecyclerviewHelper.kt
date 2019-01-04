package com.jojo.design.common_base.utils

import android.app.Activity
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import com.jojo.design.common_base.R
import com.jojo.design.common_base.adapter.rv.MultiItemTypeAdapter
import com.jojo.design.common_ui.lrecyclerview.recyclerview.LRecyclerView
import com.jojo.design.common_ui.lrecyclerview.recyclerview.LRecyclerViewAdapter
import com.jojo.design.common_ui.lrecyclerview.recyclerview.ProgressStyle

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/24 3:33 PM
 *    desc   :
 */
class RecyclerviewHelper {
    companion object {
        fun initRecyclerView(mRecyclerView: LRecyclerView, mAdapter: MultiItemTypeAdapter<out Any>, mContext: Context) {
            val mLRecyclerViewAdapter = LRecyclerViewAdapter(mAdapter)
            //设置外层列表Adapter
            mRecyclerView.layoutManager = LinearLayoutManager(mContext)
            mRecyclerView.adapter = mLRecyclerViewAdapter
            mRecyclerView.setHasFixedSize(true)
            mRecyclerView.setRefreshProgressStyle(ProgressStyle.SysProgress)
            mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SysProgress)
            //设置头部文字颜色
            mRecyclerView.setHeaderViewColor(R.color.color_app_yellow, R.color.color_app_yellow, R.color.color_ffffff)
            //设置底部加载颜色-loading动画颜色,文字颜色,footer的背景颜色
            mRecyclerView.setFooterViewColor(R.color.color_app_yellow, R.color.color_app_yellow, R.color.color_ffffff)
            //设置底部加载文字提示
            mRecyclerView.setFooterViewHint(mContext.resources.getString(R.string.list_footer_loading), mContext.resources.getString(R.string.list_footer_end), mContext.resources.getString(R.string.list_footer_network_error))

        }

        fun initLayoutManagerRecyclerView(mRecyclerView: LRecyclerView, mAdapter: MultiItemTypeAdapter<out Any>, layoutManager: RecyclerView.LayoutManager, mContext: Context) {
            //设置外层列表Adapter
            val mLRecyclerViewAdapter = LRecyclerViewAdapter(mAdapter)
            //必须先设置布局管理器，否则LayoutManager为GridLayoutManager时，下拉刷新无法正常展示
            mRecyclerView.layoutManager = layoutManager
            mRecyclerView.adapter = mLRecyclerViewAdapter
            mRecyclerView.setHasFixedSize(true)
            mRecyclerView.setRefreshProgressStyle(ProgressStyle.SysProgress)
            mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SysProgress)
            //设置头部文字颜色
            mRecyclerView.setHeaderViewColor(R.color.color_app_yellow, R.color.color_app_yellow, R.color.color_ffffff)
            //设置底部加载颜色-loading动画颜色,文字颜色,footer的背景颜色
            mRecyclerView.setFooterViewColor(R.color.color_app_yellow, R.color.color_app_yellow, R.color.color_ffffff)
            //设置底部加载文字提示
            mRecyclerView.setFooterViewHint(mContext.resources.getString(R.string.list_footer_loading), mContext.resources.getString(R.string.list_footer_end), mContext.resources.getString(R.string.list_footer_network_error))

        }

        /**
         * 添加HeaderView的RecyclerView
         * @param mContext
         * @param headView
         * @param mRecyclerView
         * @param mLRecyclerViewAdapter
         * @param footer_note
         */
        fun initHeaderRecyclerView(mContext: Context, headView: View, mRecyclerView: LRecyclerView, mLRecyclerViewAdapter: LRecyclerViewAdapter, footer_note: String) {
            mLRecyclerViewAdapter.addHeaderView(headView)
            //设置外层列表Adapter
            mRecyclerView.adapter = mLRecyclerViewAdapter
            mRecyclerView.setHasFixedSize(true)
            mRecyclerView.layoutManager = LinearLayoutManager(mContext)
            mRecyclerView.setRefreshProgressStyle(ProgressStyle.SysProgress)
            mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SysProgress)
            //设置头部文字颜色
            mRecyclerView.setHeaderViewColor(R.color.color_app_yellow, R.color.color_app_yellow, R.color.color_ffffff)
            //设置底部加载颜色-loading动画颜色,文字颜色,footer的背景颜色
            mRecyclerView.setFooterViewColor(R.color.color_app_yellow, R.color.color_app_yellow, R.color.color_ffffff)
            //设置底部加载文字提示
            if (!TextUtils.isEmpty(footer_note)) {
                mRecyclerView.setFooterViewHint(mContext.resources.getString(R.string.list_footer_loading), footer_note, mContext.resources.getString(R.string.list_footer_network_error))
            } else {
                mRecyclerView.setFooterViewHint(mContext.resources.getString(R.string.list_footer_loading), mContext.resources.getString(R.string.list_footer_end), mContext.resources.getString(R.string.list_footer_network_error))
            }
        }

        fun initNormalRecyclerView(mRecyclerView: RecyclerView, mAdapter: MultiItemTypeAdapter<out Any>, layoutManager: RecyclerView.LayoutManager) {
            mRecyclerView.layoutManager = layoutManager
            mRecyclerView.adapter = mAdapter
        }

        //初始化RecyclerView
        fun initNormalRecyclerView(context: Activity, mRecyclerView: RecyclerView, mAdapter: RecyclerView.Adapter<*>, layoutManager: RecyclerView.LayoutManager) {
            mRecyclerView.layoutManager = layoutManager
            mRecyclerView.adapter = mAdapter
        }
    }
}