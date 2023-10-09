package com.jojo.design.module_test.demo

import android.content.Context
import com.jojo.design.common_base.adapter.rv.CommonAdapter
import com.jojo.design.common_base.adapter.rv.ViewHolder
import com.jojo.design.module_test.R

//自定义Behavior示例：https://blog.51cto.com/u_15375308/5073098
//Android进阶CoordinatorLayout协调者布局实现吸顶效果: https://www.jb51.net/article/273609.htm
class ADA_ListItem constructor(context: Context) : CommonAdapter<Int>(context) {
    override fun itemLayoutId(): Int = R.layout.item_list

    override fun convert(holder: ViewHolder, bean: Int, position: Int) {
        holder.setText(R.id.error_view_tv, bean.toString())
    }
}
