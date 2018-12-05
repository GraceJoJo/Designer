package com.jojo.design

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route

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
        setContentView(R.layout.act_test)
    }
}
