package com.jojo.design.module_core.adapter

import android.content.Context
import com.jojo.design.common_base.adapter.lv.CommonAdapterListView
import com.jojo.design.common_base.adapter.lv.ViewHolderListView
import com.jojo.design.common_base.utils.glide.GlideUtils
import com.jojo.design.module_core.R
import com.jojo.design.module_core.bean.CategoryEntity

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/13 6:14 PM
 *    desc   :
 */
class ADA_ItemGoodsCategory constructor(context: Context) : CommonAdapterListView<CategoryEntity>(context) {
    override fun convert(holder: ViewHolderListView, bean: CategoryEntity, position: Int) {
        GlideUtils.loadImage(bean.logo, holder.getView(R.id.iv_category_logo), 0)
        holder.setText(R.id.tv_category_name, bean.name)
    }
    override fun itemLayoutId(): Int = R.layout.item_goods_category
}