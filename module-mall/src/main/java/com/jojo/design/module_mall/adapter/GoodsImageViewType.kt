package com.jojo.design.module_mall.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.adapter.rv.ItemViewDelegate
import com.jojo.design.common_base.adapter.rv.ViewHolder
import com.jojo.design.common_base.net.API
import com.jojo.design.common_base.utils.glide.GlideUtils
import com.jojo.design.common_base.utils.glide.transform.CornerOriginSizeTransform
import com.jojo.design.module_mall.R
import com.jojo.design.module_mall.bean.GoodsDesBean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/1/16 10:02 PM
 *    desc   : 商品详情
 */
class GoodsImageViewType constructor() : ItemViewDelegate<GoodsDesBean> {
    override fun convert(holder: ViewHolder, bean: GoodsDesBean, position: Int) {


        //图片展示大小会有问题，设置ScaleType也不好使
//        GlideUtils.loadImage(bean.content, holder.getView(R.id.iv_content), 0)

        GlideUtils.loadOriginalSizeImage(bean.content, holder.getView(R.id.iv_content), 0)
    }

    override fun isForViewType(item: GoodsDesBean, position: Int): Boolean = item.isImg


    override fun getItemViewLayoutId(): Int = R.layout.item_goods_des_image
}