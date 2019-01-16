package com.jojo.design.module_mall.adapter

import android.content.Context
import com.jojo.design.common_base.adapter.lv.CommonAdapterListView
import com.jojo.design.common_base.adapter.lv.ViewHolderListView
import com.jojo.design.common_base.utils.glide.GlideUtils
import com.jojo.design.module_mall.R
import com.jojo.design.module_mall.bean.CommentBean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/15 4:10 PM
 *    desc   : 商品内容（图片+文字）
 */
class ADA_GoodsComment constructor(context: Context) : CommonAdapterListView<CommentBean>(context) {
    override fun itemLayoutId(): Int = R.layout.item_comment

    override fun convert(holder: ViewHolderListView, bean: CommentBean, position: Int) {
        holder.setText(R.id.tv_nick, bean.nick)
        holder.setText(R.id.tv_content, bean.content)
        holder.setText(R.id.tv_time, bean.time)
        GlideUtils.loadCircleImage(bean.avaPath, holder.getView(R.id.iv_avaPath), 0)
    }
}