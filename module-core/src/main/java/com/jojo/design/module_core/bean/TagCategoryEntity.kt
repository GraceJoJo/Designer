package com.jojo.design.module_core.bean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/7 2:42 PM
 *    desc   : 设计师-标签类别实体
 */
data class TagCategoryEntity(private val id: String,  val name: String,  val tags: List<TagBean>) {
    data class TagBean(private val categoryId: String, private val id: String, val image: String,  val name: String) {

    }
}