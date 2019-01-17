package com.jojo.design.module_core.bean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/17 3:12 PM
 *    desc   : 专题详情（一级）
 */
data class TopicDetailEntity(var list: List<BannerBean>, var name: String, var tagid: String) {
    data class BannerBean(var image: String, var text: String)
}