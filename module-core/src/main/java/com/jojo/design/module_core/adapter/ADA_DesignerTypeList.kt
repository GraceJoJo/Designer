package com.jojo.design.module_core.adapter

import android.content.Context
import com.bumptech.glide.load.model.GlideUrl
import com.jojo.design.common_base.adapter.lv.CommonAdapterListView
import com.jojo.design.common_base.adapter.lv.ViewHolderListView
import com.jojo.design.common_base.utils.GlideUtils
import com.jojo.design.module_core.R
import com.jojo.design.module_core.bean.TagCategoryEntity

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/10 4:48 PM
 *    desc   : 设计分类标签Adapter
 */
class ADA_DesignerTypeList constructor(context: Context) : CommonAdapterListView<TagCategoryEntity.TagBean>(context) {
    override fun itemLayoutId(): Int = R.layout.item_designer_tag

    override fun convert(holder: ViewHolderListView, bean: TagCategoryEntity.TagBean, position: Int) {
        GlideUtils.loadImage(bean.image, holder.getView(R.id.iv_cover), 0)
        holder.setText(R.id.tv_type, bean.name)
    }
}