package com.jojo.design.module_discover.bean

import java.io.Serializable

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/18 12:42 PM
 *    desc   : 视频分类
 */
data class CategoryBean(var id: String, var name: String, var description: String, var bgPicture: String, var headerImage: String) : Serializable {
}