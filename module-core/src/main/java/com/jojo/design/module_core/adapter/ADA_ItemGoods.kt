package com.jojo.design.module_core.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.alibaba.android.arouter.launcher.ARouter
import com.jojo.design.common_base.adapter.rv.CommonAdapter
import com.jojo.design.common_base.adapter.rv.ViewHolder
import com.jojo.design.common_base.config.arouter.ARouterConfig
import com.jojo.design.common_base.config.arouter.ARouterConstants
import com.jojo.design.common_base.utils.glide.GlideUtils
import com.jojo.design.module_core.R
import com.jojo.design.module_core.bean.GoodsEntity

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/13 6:14 PM
 *    desc   : 逛-（图片+子列表）适配器
 */
class ADA_ItemGoods constructor(context: Context) : CommonAdapter<GoodsEntity>(context) {
    override fun convert(holder: ViewHolder, bean: GoodsEntity, position: Int) {
        GlideUtils.loadImage(bean.image, holder.getView<ImageView>(R.id.iv_image), 0)
        holder.setText(R.id.tv_name, bean.name)
        holder.setText(R.id.tv_title, bean.title)
        val rvGoods = holder.getView<RecyclerView>(R.id.rl_goods)
        var adapter = ADA_ChildGoods(mContext!!)
        var rv = holder.getView<RecyclerView>(R.id.rv)
        rvGoods.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        rvGoods.adapter = adapter
        adapter.update(bean.items, true)

        holder.setOnClickListener(R.id.iv_image, {
            ARouter.getInstance().build(ARouterConfig.ACT_WEBVIEW)
                    .withString(ARouterConstants.WEB_TITLE, bean.name)
                    .withString(ARouterConstants.WEB_URL, bean.h5Url)
                    .navigation()
        })
    }

    override fun itemLayoutId(): Int = R.layout.item_goods
}