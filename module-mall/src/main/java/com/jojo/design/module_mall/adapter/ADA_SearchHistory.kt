package com.jojo.design.module_mall.adapter

import android.content.Context
import com.jojo.design.common_base.adapter.rv.CommonAdapter
import com.jojo.design.common_base.adapter.rv.ViewHolder
import com.jojo.design.module_mall.R
import com.jojo.design.module_mall.db.bean.SearchHistoryBean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/29 3:45 PM
 *    desc   : 搜索历史
 */
class ADA_SearchHistory constructor(context: Context) : CommonAdapter<SearchHistoryBean>(context) {
    override fun itemLayoutId(): Int = R.layout.item_search_history

    override fun convert(holder: ViewHolder, bean: SearchHistoryBean, position: Int) {
        holder.setText(R.id.tv_keyword, bean.searchKeyWords)
    }
}