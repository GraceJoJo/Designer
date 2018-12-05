package com.jojo.design.common_base.bean

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2018/12/2 1:49 PM
 * desc   :
 */
class DataBean<T> {
    var content: T? = null
    var numberOfElements: Int = 0
    var size: Int = 0
    var totalElements: Int = 0
    var totalPages: Int = 0
}
