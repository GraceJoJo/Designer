package com.jojo.design.module_core.dagger2

import com.jojo.design.module_discover.ui.ACT_Category
import com.jojo.design.module_discover.ui.ACT_CategoryDetail
import com.jojo.design.module_discover.ui.FRA_CategoryDetail
import com.will.weiyuekotlin.component.ApplicationComponent
import dagger.Component

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/18 14:29 PM
 *    desc   : Dagger2
 */
@Component(dependencies = [(ApplicationComponent::class)])
interface FoundComponent {
    fun inject(activity: ACT_Category)
    fun inject(activity: ACT_CategoryDetail)
    fun inject(activity: FRA_CategoryDetail)
}