package com.jojo.design.module_core.adapter

import android.app.Activity
import android.content.Context
import com.jojo.design.common_base.adapter.rv.MultiItemTypeAdapter
import com.jojo.design.module_core.bean.ContentBean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/12 9:32 PM
 *    desc   : 逛
 */
class ADA_ShoppingContent constructor(context: Context) : MultiItemTypeAdapter<ContentBean>(context) {

    init {
        super.mContext = context
        addItemViewDelegate(GoodsCategoryViewType(context))
        addItemViewDelegate(GoodsViewType(context))
//        addItemViewDelegate(ViewPagerViewType())
    }

}