package com.jojo.design

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.alibaba.android.arouter.facade.annotation.Route
import com.jojo.design.common_base.bean.ErrorBean
import com.jojo.design.databinding.ActTestBinding

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2018/11/29 5:22 PM
 * desc   :
 */
@Route(path = "/appmodule/test")
class ACT_TestRouter : AppCompatActivity() {
    //    @BindView(R.id.tv) lateinit var tv :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val viewDataBinding = DataBindingUtil.setContentView<ActTestBinding>(this, R.layout.act_test)
//        var bean = ErrorBean()
//        bean.msg = "DataBinding测试"
//        viewDataBinding?.bean = bean
        var viewDataBinding = DataBindingUtil.inflate<ActTestBinding>(LayoutInflater.from(this), R.layout.act_test, null, false)
        setContentView(viewDataBinding.root)
        var bean = ErrorBean()
        bean.msg = "DataBinding测试"
        viewDataBinding?.bean = bean
    }
}
