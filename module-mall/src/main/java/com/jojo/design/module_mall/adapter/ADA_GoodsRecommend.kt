package com.jojo.design.module_mall.adapter

import android.content.Context
import com.jojo.design.common_base.adapter.lv.CommonAdapterListView
import com.jojo.design.common_base.adapter.lv.ViewHolderListView
import com.jojo.design.common_base.utils.glide.GlideUtils
import com.jojo.design.module_mall.R
import com.jojo.design.module_mall.bean.RevelentBean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/15 4:10 PM
 *    desc   : 商品内容（图片+文字）
 */
class ADA_GoodsRecommend constructor(context: Context) : CommonAdapterListView<RevelentBean.RevelentItemBean>(context) {
    override fun itemLayoutId(): Int = R.layout.item_goods_recommend

    override fun convert(holder: ViewHolderListView, bean: RevelentBean.RevelentItemBean, position: Int) {
        GlideUtils.loadImage(bean.imageUrl, holder.getView(R.id.iv_image), 0)
    }
}