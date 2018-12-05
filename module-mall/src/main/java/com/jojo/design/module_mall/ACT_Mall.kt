package com.jojo.design.module_mall

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jojo.design.module_core.R

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/4 10:08 AM
 *    desc   :
 */
@Route(path = "/mall/act_mall")
class ACT_Mall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_mall)

    }
}