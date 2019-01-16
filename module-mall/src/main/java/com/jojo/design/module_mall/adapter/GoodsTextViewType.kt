package com.jojo.design.module_mall.adapter

import android.content.Context
import com.jojo.design.common_base.BaseAppliction.Companion.context
import com.jojo.design.common_base.adapter.rv.ItemViewDelegate
import com.jojo.design.common_base.adapter.rv.ViewHolder
import com.jojo.design.module_mall.R
import com.jojo.design.module_mall.bean.GoodsDesBean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/1/16 10:02 PM
 *    desc   : 商品详情
 */
class GoodsTextViewType constructor() : ItemViewDelegate<GoodsDesBean> {
    override fun convert(holder: ViewHolder, bean: GoodsDesBean, position: Int) {
        holder.setText(R.id.tv_content, bean.content)
    }

    override fun isForViewType(item: GoodsDesBean, position: Int): Boolean = !item.isImg

    override fun getItemViewLayoutId(): Int = R.layout.item_goods_des_text
}