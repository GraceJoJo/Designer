package com.jojo.design.module_mall.adapter

import android.content.Context
import com.jojo.design.common_base.adapter.rv.MultiItemTypeAdapter
import com.jojo.design.module_mall.bean.GoodsDesBean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/15 4:10 PM
 *    desc   : 商品内容（图片+文字）
 */
class ADA_GoodsDes constructor(context: Context) : MultiItemTypeAdapter<GoodsDesBean>(context) {
    init {
        super.mContext = context
        addItemViewDelegate(GoodsImageViewType())
        addItemViewDelegate(GoodsTextViewType())
    }
}