package com.jojo.design.module_core.adapter

import android.content.Context
import android.widget.ImageView
import com.jojo.design.common_base.adapter.lv.CommonAdapterListView
import com.jojo.design.common_base.adapter.lv.ViewHolderListView
import com.jojo.design.common_base.utils.GlideUtils
import com.jojo.design.module_core.R
import com.jojo.design.module_core.bean.AllfaverEntity

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/20 5:56 PM
 *    desc   : 大家喜欢，子列表适配器
 */
class ADA_PersonChildLike constructor(context: Context) : CommonAdapterListView<AllfaverEntity.FaverBean.FeedBean>(context) {
    override fun convert(holder: ViewHolderListView, bean: AllfaverEntity.FaverBean.FeedBean, position: Int) {
        GlideUtils.loadImage(bean.image, holder.getView<ImageView>(R.id.iv_image), 0)
        holder.setText(R.id.tv_favNum, bean.favNum.toString())
    }


    override fun itemLayoutId(): Int = R.layout.item_person_child_like
}