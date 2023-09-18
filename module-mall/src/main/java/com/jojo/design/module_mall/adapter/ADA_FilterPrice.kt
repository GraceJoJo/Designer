package com.jojo.design.module_mall.adapter

import android.content.Context
import androidx.core.content.ContextCompat
import com.jojo.design.common_base.adapter.lv.CommonAdapterListView
import com.jojo.design.common_base.adapter.lv.ViewHolderListView
import com.jojo.design.module_mall.R
import com.jojo.design.module_mall.bean.FilterBean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/1/9 17:56 PM
 *    desc   : 筛选框中的折扣和服务 适配器 价格区间列表适配器
 */
class ADA_FilterPrice constructor(context: Context) : CommonAdapterListView<String>(context) {
    var mSelectPos = -1
    override fun convert(holder: ViewHolderListView, bean: String, position: Int) {
        holder.setText(R.id.tv_value, bean)
        if (mSelectPos == position) {
            holder.setTextColor(R.id.tv_value, ContextCompat.getColor(mContext, R.color.color_ffffff))
            holder.setBackgroundRes(R.id.tv_value, R.drawable.bg_shape_app_yellow_6)
        } else {
            holder.setTextColor(R.id.tv_value, ContextCompat.getColor(mContext, R.color.color_858585))
            holder.setBackgroundRes(R.id.tv_value, R.drawable.bg_shape_ffffff_6)
        }
    }

    override fun itemLayoutId(): Int = R.layout.item_filter_price


}