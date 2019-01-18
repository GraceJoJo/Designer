package com.jojo.design.module_discover.adapter

import android.content.Context
import android.widget.ImageView
import com.jojo.design.common_base.adapter.rv.CommonAdapter
import com.jojo.design.common_base.adapter.rv.ViewHolder
import com.jojo.design.common_base.utils.glide.GlideUtils
import com.jojo.design.module_discover.R
import com.jojo.design.module_discover.bean.CategoryBean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/18 5:37 PM
 *    desc   : 分类列表
 */
class ADA_Category constructor(context: Context) : CommonAdapter<CategoryBean>(context) {
    override fun itemLayoutId(): Int = R.layout.item_category

    override fun convert(holder: ViewHolder, bean: CategoryBean, position: Int) {
        GlideUtils.loadImage(bean.bgPicture, holder.getView<ImageView>(R.id.iv_bg_img), 0)
        holder.setText(R.id.tv_name, "#" + bean.name)
    }
}