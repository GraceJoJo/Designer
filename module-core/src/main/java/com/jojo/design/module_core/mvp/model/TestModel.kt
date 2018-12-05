package com.jojo.design.module_core.mvp.model

import com.jojo.design.module_core.mvp.contract.TestContract
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/5 10:51 AM
 *    desc   : Dagger2_MVP-Model
 */
class TestModel @Inject constructor() : TestContract.Model {
    override fun getData(): String = "Dagger2+MVP测试成功咯"
}