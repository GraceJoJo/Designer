package com.jojo.design.module_discover.adapter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jojo.design.common_base.adapter.rv.ItemViewDelegate
import com.jojo.design.common_base.adapter.rv.ViewHolder
import com.jojo.design.module_discover.R
import com.jojo.design.module_discover.bean.ItemEntity

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/24 3:52 PM
 *    desc   : type=videoCollectionOfHorizontalScrollCard
 *    item.type == "videoCollectionWithBrief"时，为请求getCategoryAuthor和getCategoryPlaylist的数据
 */
class ScrollCardType constructor(context: Context) : ItemViewDelegate<ItemEntity.ItemDataEntity> {
    private var mContext: Context? = null

    init {
        mContext = context
    }

    override fun getItemViewLayoutId(): Int = R.layout.item_scrollcard_type

    override fun isForViewType(item: ItemEntity.ItemDataEntity, position: Int): Boolean = (item.type == "videoCollectionOfHorizontalScrollCard" || item.type == "videoCollectionWithBrief")

    override fun convert(holder: ViewHolder, bean: ItemEntity.ItemDataEntity, position: Int) {
        if (bean.type == "videoCollectionWithBrief" && bean.data.header != null) {
            holder.setVisible(R.id.tv_des, true)
            holder.setText(R.id.tv_des, bean?.data?.header?.description)
        } else {
            holder.setText(R.id.tv_des, "")
            holder.setVisible(R.id.tv_des, false)
        }
        holder.setText(R.id.tv_title, bean?.data?.header?.title)
        var rv_card = holder.getView<RecyclerView>(R.id.rv_card)
        rv_card.layoutManager = LinearLayoutManager(
            mContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        var adapter = ADA_ItemCard(mContext!!)
        rv_card.adapter = adapter
        adapter.update(bean.data.itemList, true)
    }
}