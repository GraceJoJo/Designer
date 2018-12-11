package com.jojo.design.common_base.net

import com.jojo.design.common_base.bean.DataBean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/2 1:49 PM
 *    desc   : 数据格式统一封装(data下嵌套了json)
 */
class BaseResponse<T>(code: Int, msg: String, data: T) {
    var code: Int = 0
    var msg: String? = null
    var success: Boolean = false
    var data: DataBean<T>? = null
}