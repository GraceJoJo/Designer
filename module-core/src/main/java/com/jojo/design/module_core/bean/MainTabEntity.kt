package com.jojo.design.module_core.bean

import com.flyco.tablayout.listener.CustomTabEntity

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/7 1:05 PM
 *    desc   :
 */
class MainTabEntity (var title: String, private var selectedIcon: Int, private var unSelectedIcon: Int) : CustomTabEntity {
    override fun getTabUnselectedIcon(): Int  = unSelectedIcon

    override fun getTabSelectedIcon(): Int  = selectedIcon

    override fun getTabTitle(): String  = title
}