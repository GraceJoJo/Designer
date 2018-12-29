package com.smart.novel.adapter

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.jojo.design.common_base.BaseAppliction.Companion.context
import com.jojo.design.common_ui.view.tag.FlowLayout
import com.jojo.design.common_ui.view.tag.TagAdapter
import com.jojo.design.module_core.R

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/29 10:08 AM
 *    desc   : 热门搜索标签
 */
class ADA_HotSearchTag constructor(hotList: List<String>) : TagAdapter<String>(hotList) {
    override fun getView(parent: FlowLayout?, position: Int, name: String?): View {
        var itemTagView = LayoutInflater.from(context).inflate(R.layout.item_hot_search, null);
        itemTagView.findViewById<TextView>(R.id.tv_item_name).text = name
        return itemTagView
    }
}