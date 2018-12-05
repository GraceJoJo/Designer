package com.jojo.design.common_base.dagger.mvp

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/5 3:49 PM
 *    desc   : Fragment懒加载抽离的接口
 */
interface IBaseLazyFragment {
    fun onFirstUserVisible()
    fun onFirstUserInvisible()
    fun onUserVisible()
    fun onUserInvisible()
}