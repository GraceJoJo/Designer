package com.jojo.design.module_discover.bean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/23 8:15 PM
 *    desc   :
 */
data class TabEntity(var tabInfo: TabInfoEntity) {
    data class TabInfoEntity(var tabList: List<TabBean>) {
        data class TabBean(var id: String, var name: String, var apiUrl: String)
    }
}