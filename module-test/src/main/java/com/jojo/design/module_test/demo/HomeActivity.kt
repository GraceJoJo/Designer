package com.jojo.design.module_test.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jojo.design.common_base.utils.RecyclerviewHelper
import com.jojo.design.module_test.R
import kotlinx.android.synthetic.main.layout_behavior.rv

/**
 *    @author : JoJo
 *    @since : 2023/9/24 15:01
 *    @email :
 */
class HomeActivity : AppCompatActivity() {
    private var mAdapter: ADA_ListItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_behavior)


        mAdapter = ADA_ListItem(this)
        RecyclerviewHelper.initNormalRecyclerView(
            rv,
            mAdapter!!,
            object : LinearLayoutManager(this) {
                override fun canScrollVertically(): Boolean {
                    return true
                }
            })
        var dataList = mutableListOf<Int>()
        for (i in 0..20) {
            dataList.add(i)
        }
        mAdapter!!.update(dataList, true)

    }
}