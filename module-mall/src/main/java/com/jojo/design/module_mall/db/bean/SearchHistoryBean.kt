package com.jojo.design.module_mall.db.bean

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/8 12:16 PM
 * desc   :
 */
@Entity(tableName = "history")
//indices = [(Index(value = *arrayOf("searchKeyWords"), unique = true))]保证searchKeyWords列的唯一性
//tableName = "history", primaryKeys = ["id", "searchKeyWords"]设置多个主键（编译出错：Error:Execution failed for task ':module-mall:kaptDebugKotlin'...）
class SearchHistoryBean {
    //设置主键，并且定义自增增
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var searchKeyWords: String? = null

    override fun toString(): String {
        return "SearchHistoryBean{" +
                "id=" + id +
                ", searchKeyWords='" + searchKeyWords + '\'' +
                '}'
    }
}
