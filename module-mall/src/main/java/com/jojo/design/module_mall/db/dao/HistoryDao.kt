package com.jojo.design.module_mall.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.jojo.design.module_mall.db.bean.SearchHistoryBean


/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/2 3:11 PM
 *    desc   : 搜索历史-Dao类
 */
@Dao
interface HistoryDao {
    /**
     * 插入记搜索记录
     */
    @Insert
    fun insert(historyBean: SearchHistoryBean)

    @Query("SELECT * FROM search_history")
    fun getAllHistory(): List<SearchHistoryBean>
}