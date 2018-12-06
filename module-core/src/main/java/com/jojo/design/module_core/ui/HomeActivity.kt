package com.jojo.design.module_core.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.jojo.design.common_base.bean.ErrorBean
import com.jojo.design.module_core.R
import com.jojo.design.module_core.databinding.TestBinding

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/2 2:53 PM
 *    desc   : 首页
 */
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var viewDataBinding = DataBindingUtil.inflate<TestBinding>(LayoutInflater.from(this), R.layout.test, null, false)
        setContentView(viewDataBinding.root)
//        DataBindingUtil.setContentView<TestBinding>(this,R.layout.test)
        var bean = ErrorBean()
        bean.msg = "DataBinding测试"
        viewDataBinding?.bean = bean
    }
}