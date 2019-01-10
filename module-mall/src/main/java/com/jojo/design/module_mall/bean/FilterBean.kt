package com.jojo.design.module_mall.bean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/9 12:04 PM
 *    desc   :
 */
data class FilterBean(var promotionTags: List<PromotionTagBean>, var stageRange: List<String>) {
    data class PromotionTagBean(var key: String, var value: String, var isCheck: Boolean)
}