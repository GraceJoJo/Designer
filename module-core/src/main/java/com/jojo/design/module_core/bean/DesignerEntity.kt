package com.jojo.design.module_core.bean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/7 3:31 PM
 *    desc   :
 */
data class DesignerEntity(private val id: String, var tags: List<TagBean>, var userNick: String, val banner: String, val opTag: String, val shopName: String, val shopId: String) {
    data class TagBean(val name: String)
}