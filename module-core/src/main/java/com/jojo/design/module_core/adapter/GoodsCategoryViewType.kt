package com.jojo.design.module_core.adapter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.jojo.design.common_base.adapter.rv.ItemViewDelegate
import com.jojo.design.common_base.adapter.rv.ViewHolder
import com.jojo.design.common_ui.view.NoScrollGridView
import com.jojo.design.module_core.R
import com.jojo.design.module_core.R.id.rv
import com.jojo.design.module_core.bean.ContentBean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/12 10:02 PM
 *    desc   :
 */
class GoodsCategoryViewType constructor(context: Context) : ItemViewDelegate<ContentBean> {
    var mContext: Context? = null

    init {
        mContext = context
    }

    override fun getItemViewLayoutId(): Int = R.layout.goods_category_view_type

    override fun isForViewType(item: ContentBean, position: Int): Boolean {
        return item.type == 1
    }

    override fun convert(holder: ViewHolder, bean: ContentBean, position: Int) {
        val gv = holder.getView<NoScrollGridView>(R.id.gv)
        var adapter = ADA_ItemGoodsCategory(mContext!!)
//        rv.layoutManager = GridLayoutManager(mContext, 4)
        gv.adapter = adapter
        adapter.update(bean.categorys, true)
    }
}