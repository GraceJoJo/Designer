package com.jojo.design.module_core.dagger2

import com.jojo.design.module_core.ui.designer.ACT_DesignerList
import com.jojo.design.module_core.ui.home.*
import com.jojo.design.module_core.ui.test.TestDaggerActivity
import com.jojo.design.module_core.ui.test.TestFragment
import com.jojo.design.module_core.ui.test.TestMVPActivity
import com.will.weiyuekotlin.component.ApplicationComponent
import dagger.Component

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/5 10:19 AM
 *    desc   :
 */
@Component(dependencies = [(ApplicationComponent::class)])
interface CoreComponent {
    fun inject(activity: TestMVPActivity)
    fun inject(activity: TestDaggerActivity)
    fun inject(fragment: TestFragment)
    fun inject(fragment: DesignerFragment)
    fun inject(fragment: ShoppingFragmentOld)
    fun inject(fragment: ShoppingFragment)
    fun inject(fragment: HandpickedFragment)
    fun inject(fragment: PersonLikeFragment)
    fun inject(activity: ACT_DesignerList)
}