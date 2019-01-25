package com.jojo.design.module_core.bean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/21 2:05 PM
 *    desc   : tab-大家喜欢
 */
data class AllfaverEntity(var time: String, var list: List<FaverBean>, var date: String) {
    data class FaverBean(var avatarPath: String, var feeds: List<FeedBean>, var nickName: String, var time: String, var feedsSize: Int) {
        data class FeedBean(var id: String, var favNum: Int, var image: String) {
        }
    }
}