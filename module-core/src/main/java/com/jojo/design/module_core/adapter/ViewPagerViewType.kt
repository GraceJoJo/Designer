package com.jojo.design.module_core.adapter

import com.jojo.design.common_base.adapter.rv.ItemViewDelegate
import com.jojo.design.common_base.adapter.rv.ViewHolder
import com.jojo.design.module_core.R
import com.jojo.design.module_core.bean.ContentBean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/12 10:02 PM
 *    desc   :
 */
class ViewPagerViewType : ItemViewDelegate<ContentBean> {
    override fun getItemViewLayoutId(): Int = R.layout.item_viewpager

    override fun isForViewType(item: ContentBean, position: Int): Boolean {
        return item.type ==0
    }

    override fun convert(holder: ViewHolder?, t: ContentBean?, position: Int) {
    }
}