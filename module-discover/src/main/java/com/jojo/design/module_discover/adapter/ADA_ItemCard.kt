package com.jojo.design.module_discover.adapter

import android.content.Context
import android.widget.ImageView
import com.jojo.design.common_base.adapter.rv.CommonAdapter
import com.jojo.design.common_base.adapter.rv.ViewHolder
import com.jojo.design.common_base.utils.DataFormatUtils
import com.jojo.design.common_base.utils.glide.GlideUtils
import com.jojo.design.module_discover.R
import com.jojo.design.module_discover.bean.ItemEntity

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/24 5:37 PM
 *    desc   : 分类详情-横向子列表
 */
class ADA_ItemCard constructor(context: Context) : CommonAdapter<ItemEntity.ItemDataEntity.DataEntity.ItemBean>(context) {
    override fun itemLayoutId(): Int = R.layout.item_card

    override fun convert(holder: ViewHolder, bean: ItemEntity.ItemDataEntity.DataEntity.ItemBean, position: Int) {
        GlideUtils.loadNormalImage(bean?.data?.cover?.detail, holder.getView<ImageView>(R.id.iv_card), 0)
        holder.setText(R.id.tv_size, DataFormatUtils.formatTime((bean?.data?.playInfo[0]?.urlList[0]?.size) / 100))
    }
}