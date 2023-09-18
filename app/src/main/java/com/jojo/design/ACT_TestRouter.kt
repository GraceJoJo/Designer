package com.jojo.design

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var viewDataBinding = DataBindingUtil.inflate<ActTestBinding>(LayoutInflater.from(this), R.layout.act_test, null, false)
        setContentView(viewDataBinding.root)
        var bean = ErrorBean()
        bean.msg = "DataBinding测试"
        viewDataBinding?.bean = bean
    }
}
