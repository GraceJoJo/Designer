package com.jojo.design.module_mall.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.jojo.design.module_mall.db.bean.SearchHistoryBean
import com.jojo.design.module_mall.db.dao.HistoryDao

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/2 3:17 PM
 *    desc   :
 */
@Database(entities = arrayOf(SearchHistoryBean::class, SearchHistoryBean::class), version = 1)
abstract class AppDataBase constructor(context: Context) : RoomDatabase() {
    private var DB_NAME = "designer.db"
    @Volatile
    private var instance: AppDataBase? = null

//    https://www.jianshu.com/p/cfde3535233d
    @Synchronized
     fun getInstance(context: Context): AppDataBase {
        if (instance == null) {
            instance = create(context)
        }
        return instance!!
    }

   open fun create(context: Context): AppDataBase? = Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME).build()

    abstract fun historyDao(): HistoryDao

}