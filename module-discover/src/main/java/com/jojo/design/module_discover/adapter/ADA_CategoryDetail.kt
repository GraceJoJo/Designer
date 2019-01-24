package com.jojo.design.module_discover.adapter

import android.app.Activity
import com.jojo.design.common_base.adapter.rv.MultiItemTypeAdapter
import com.jojo.design.module_discover.bean.ItemEntity

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/1/24 3:32 PM
 *    desc   : 分类详情-分类型列表
 */
class ADA_CategoryDetail constructor(context: Activity) : MultiItemTypeAdapter<ItemEntity.ItemDataEntity>(context) {
    init {
        super.mContext = context
        addItemViewDelegate(ScrollCardType(context))
        addItemViewDelegate(TextViewType(context))
        addItemViewDelegate(VideoViewType(context))
    }

}