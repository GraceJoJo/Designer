package com.jojo.design.module_core.bean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/12 9:47 PM
 *    desc   : 逛（列表）包装的bean
 */
data class ContentBean(val type: Int, val categorys: List<CategoryEntity>, val goods: List<GoodsEntity>) {
}