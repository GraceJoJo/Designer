package com.jojo.design.module_core.adapter

import android.content.Context
import com.example.jojo.databindingadapter.recyclerView.CommonAdapter
import com.example.jojo.databindingadapter.recyclerView.ViewHolder
import com.jojo.design.module_core.R
import com.jojo.design.module_core.databinding.ItemTestBinding

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/5 5:43 PM
 *    desc   :
 */
class ADA_TestFragment constructor(context: Context) : CommonAdapter<String, ItemTestBinding>(context) {
    override fun convert(viewBinding: ItemTestBinding?, holder: ViewHolder.BindingHolder?, p2: String?, position: Int) {

    }

    override fun itemLayoutId(): Int = R.layout.item_test
}