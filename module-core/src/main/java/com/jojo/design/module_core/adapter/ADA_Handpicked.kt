package com.jojo.design.module_core.adapter

import android.content.Context
import android.widget.ImageView
import com.jojo.design.common_base.adapter.lv.CommonAdapterListView
import com.jojo.design.common_base.adapter.lv.ViewHolderListView
import com.jojo.design.common_base.adapter.rv.CommonAdapter
import com.jojo.design.common_base.adapter.rv.ViewHolder
import com.jojo.design.common_base.utils.GlideUtils
import com.jojo.design.module_core.R
import com.jojo.design.module_core.bean.RecordsEntity

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/20 5:56 PM
 *    desc   :
 */
class ADA_Handpicked constructor(context: Context) : CommonAdapter<RecordsEntity.RecordsBean>(context) {
    override fun convert(holder: ViewHolder, bean: RecordsEntity.RecordsBean, position: Int) {
        GlideUtils.loadImage(bean.image, holder.getView<ImageView>(R.id.iv_image), 0)
        holder.setText(R.id.tv_price, "￥" + bean.price)
        holder.setText(R.id.tv_brand, bean.brand)
        holder.setText(R.id.tv_productDescription, bean.productDescription)
        holder.setText(R.id.tv_favNum, bean.favNum.toString())
    }
//
//    override fun convert(holder: ViewHolderListView, bean: RecordsEntity.RecordsBean, position: Int) {
//        GlideUtils.loadImage(bean.image, holder.getView<ImageView>(R.id.iv_image), 0)
//        holder.setText(R.id.tv_price, "￥" + bean.price)
//        holder.setText(R.id.tv_brand, bean.brand)
//        holder.setText(R.id.tv_productDescription, bean.productDescription)
//        holder.setText(R.id.tv_favNum, bean.favNum.toString())
//    }

    override fun itemLayoutId(): Int = R.layout.item_handpicked_goods
}