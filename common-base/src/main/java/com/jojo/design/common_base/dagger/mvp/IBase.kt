package com.jojo.design.common_base.dagger.mvp

import com.jojo.design.common_ui.view.MultipleStatusView
import com.will.weiyuekotlin.component.ApplicationComponent

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/4 9:24 PM
 *    desc   : Dagger2_MVP - Activity和Fragment抽离出的公共的接口
 */
interface IBase {
    fun getContentViewLayoutId(): Int
    fun getLoadingMultipleStatusView(): MultipleStatusView?
    fun initDaggerInject(mApplicationComponent: ApplicationComponent)
    fun startEvents()
}