package com.jojo.design.module_mall.adapter

import android.content.Context
import com.jojo.design.common_base.adapter.lv.CommonAdapterListView
import com.jojo.design.common_base.adapter.lv.ViewHolderListView
import com.jojo.design.module_mall.R
import com.jojo.design.module_mall.bean.FilterBean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/1/9 17:56 PM
 *    desc   : 筛选框中的价格区间列表适配器
 */
class ADA_FilterService constructor(context: Context) : CommonAdapterListView<FilterBean.PromotionTagBean>(context) {
    override fun convert(holder: ViewHolderListView, bean: FilterBean.PromotionTagBean, position: Int) {
        holder.setText(R.id.tv_value, bean.value)
    }

    override fun itemLayoutId(): Int = R.layout.item_filter_service

}