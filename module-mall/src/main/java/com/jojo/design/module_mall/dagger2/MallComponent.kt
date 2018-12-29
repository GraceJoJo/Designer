package com.jojo.design.module_core.dagger2

import com.jojo.design.module_mall.ui.ACT_Search
import com.will.weiyuekotlin.component.ApplicationComponent
import dagger.Component

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/5 10:19 AM
 *    desc   :
 */
@Component(dependencies = [(ApplicationComponent::class)])
interface MallComponent {
    fun inject(activity: ACT_Search)
}