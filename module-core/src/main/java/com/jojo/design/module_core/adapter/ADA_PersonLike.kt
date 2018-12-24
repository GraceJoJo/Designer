package com.jojo.design.module_core.adapter

import android.content.Context
import android.widget.ImageView
import com.jojo.design.common_base.adapter.lv.CommonAdapterListView
import com.jojo.design.common_base.adapter.lv.ViewHolderListView
import com.jojo.design.common_base.utils.GlideUtils
import com.jojo.design.common_ui.view.NoScrollGridView
import com.jojo.design.module_core.R
import com.jojo.design.module_core.bean.AllfaverEntity
import com.jojo.design.module_core.bean.RecordsEntity

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/20 5:56 PM
 *    desc   :
 */
class ADA_PersonLike constructor(context: Context) : CommonAdapterListView<AllfaverEntity.FaverBean>(context) {
    override fun convert(holder: ViewHolderListView, bean: AllfaverEntity.FaverBean, position: Int) {
        GlideUtils.loadCircleImage(bean.avatarPath, holder.getView<ImageView>(R.id.iv_avatarPath), 0)
        holder.setText(R.id.tv_nickName, bean.nickName)
        holder.setText(R.id.tv_feedsSize, "喜欢了" + bean.feedsSize + "件商品")
        holder.setText(R.id.tv_time, bean.time)
        val gridView = holder.getView<NoScrollGridView>(R.id.girdview)
        var mAdapter = ADA_PersonChildLike(mContext)
        gridView.adapter = mAdapter
        mAdapter.update(bean.feeds, true)
    }


    override fun itemLayoutId(): Int = R.layout.item_person_like
}