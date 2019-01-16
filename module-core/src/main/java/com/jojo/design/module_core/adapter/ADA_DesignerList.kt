package com.jojo.design.module_core.adapter

import android.content.Context
import android.widget.ImageView
import com.jojo.design.common_base.adapter.rv.CommonAdapter
import com.jojo.design.common_base.adapter.rv.ViewHolder
import com.jojo.design.common_base.utils.glide.GlideUtils
import com.jojo.design.module_core.R
import com.jojo.design.module_core.bean.DesignerEntity

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/24 2:43 PM
 *    desc   : 设计师列表
 */
class ADA_DesignerList constructor(context: Context) : CommonAdapter<DesignerEntity>(context) {
    override fun convert(holder: ViewHolder, bean: DesignerEntity, position: Int) {
        GlideUtils.loadCircleImage(bean.userAvatar, holder.getView<ImageView>(R.id.iv_userAvatar), 0)
        holder.setText(R.id.tv_userNick, bean.userNick)
        holder.setText(R.id.tv_opTag, bean.opTag)
        holder.setText(R.id.tv_productNum, bean.productNum.toString() + "个作品")
    }

    override fun itemLayoutId(): Int = R.layout.item_designer_list
}