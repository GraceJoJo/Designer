package com.jojo.design.module_mall.db.bean

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/29 3:46 PM
 *    desc   : 搜索记录
 */
@Entity(tableName = "search_history")
class SearchHistoryBean {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var searchKeyWords: String? = null
}