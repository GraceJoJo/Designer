package com.jojo.design.module_discover.bean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/18 2:38 PM
 *    desc   :
 */
data class ItemEntity(var itemList: List<ItemDetailEntity>, var count: Int, var total: Int) {
    data class ItemDetailEntity(var type: String, var data: DataEntity) {
        data class DataEntity(var dataType: String, var header: HeadBean, var itemList: List<ItemBean>) {
            data class HeadBean(var id: String, var title: String)
            data class ItemBean(var type: String, var data: DataItemBean) {
                data class DataItemBean(var dataType: String, var header: HeadBean, var content: ContentBean) {
                    data class ContentBean(var data: DataContentBean) {
                        data class DataContentBean(var dataType: String, var title: String, var description: String,
                                                   var playUrl: String, var cover: CoverBean, var author: AuthorBean)

                        data class CoverBean(var feed: String)
                        data class AuthorBean(var name: String)
                    }
                }
            }
        }
    }
}