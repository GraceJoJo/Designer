package com.jojo.design.module_mall.db.dao

import androidx.room.*

import com.jojo.design.module_mall.db.bean.SearchHistoryBean
import io.reactivex.Flowable

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/8 12:21 PM
 * desc   : 搜索历史-Dao类
 */
@Dao
interface HistoryDao {
    /**
     * 插入一条记录
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(historyBean: SearchHistoryBean): Long?

    @Query("SELECT * FROM history WHERE id = :id")
    fun findById(id: Int): Flowable<SearchHistoryBean>

    /**
     * 查询所有记录
     */
    @Query("SELECT * FROM history")
    fun getAllHistory(): Flowable<List<SearchHistoryBean>>

    /**
     * 删除所有记录
     */
    @Delete()
    fun deleteAll(list: List<SearchHistoryBean>): Int

    /**
     * 删除一条记录
     */
    @Delete()
    fun deleteItem(bean: SearchHistoryBean): Int
}
