package com.jojo.design.module_core.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.jojo.design.common_base.adapter.rv.CommonAdapter
import com.jojo.design.common_base.adapter.rv.ViewHolder
import com.jojo.design.common_base.utils.GlideUtils
import com.jojo.design.module_core.R
import com.jojo.design.module_core.R.id.rv
import com.jojo.design.module_core.bean.GoodsEntity

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/13 6:14 PM
 *    desc   :
 */
class ADA_ChildGoods constructor(context: Context) : CommonAdapter<GoodsEntity.ItemProductBean>(context) {
    override fun convert(holder: ViewHolder, bean: GoodsEntity.ItemProductBean, position: Int) {
        GlideUtils.loadImage(bean.image, holder.getView<ImageView>(R.id.iv_image), 0)
        holder.setText(R.id.tv_branch_name, bean.brandName)
        holder.setText(R.id.tv_keyword, bean.keyword)
        holder.setText(R.id.tv_price, "￥" + bean.price)
    }

    override fun itemLayoutId(): Int = R.layout.item_child_goods
}