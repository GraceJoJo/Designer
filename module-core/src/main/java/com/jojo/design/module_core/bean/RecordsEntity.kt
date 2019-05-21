package com.jojo.design.module_core.bean

import java.io.Serializable

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/21 12:05 PM
 *    desc   : search/list实体类
 */
data class RecordsEntity(var page: Int, var size: Int, var records: List<RecordsBean>) : Serializable {
    data class RecordsBean(var productId: String, var id: String, var avaPath: String, var brand: String, var favNum: Int, var description: String,
                           var image: String, var nickName: String, var productDescription: String,
                           var time: String, var price: String) : Serializable {
    }
}
