package com.jojo.design.module_test.component

import com.jojo.design.module_test.LoginActivity
import com.will.weiyuekotlin.component.ApplicationComponent
import dagger.Component

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/13 11:21 AM
 *    desc   :
 */
@Component(dependencies = [(ApplicationComponent::class)])
interface TestComponent {
    fun inject(activity: LoginActivity)
}