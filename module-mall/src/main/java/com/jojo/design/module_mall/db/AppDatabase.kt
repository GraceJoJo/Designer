package com.jojo.design.module_mall.db

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import android.content.Context

import com.jojo.design.module_mall.db.bean.SearchHistoryBean
import com.jojo.design.module_mall.db.dao.HistoryDao
import okhttp3.internal.Internal.instance


/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/4 5:56 PM
 * desc   :
 */
//注解指定了database的表映射实体数据以及版本等信息
@Database(entities = arrayOf(SearchHistoryBean::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    private var DB_NAME = "module_mall.db"
    @Volatile
    private var instance: AppDatabase? = null


    //https://www.jianshu.com/p/cfde3535233d
    @Synchronized
    fun getInstance(context: Context): AppDatabase {
        if (instance == null) {
            instance = create(context)
        }
        return instance!!
    }

    open fun create(context: Context): AppDatabase? = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()

    companion object {
        //数据库变动添加Migration
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DROP TABLE user")
            }
        }
        //数据库变动添加Migration  :[数据库操作语句大全(sql)](https://www.cnblogs.com/liuzheng-java/articles/sql.html)
        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //user中添加一个新字段age
//                database.execSQL("ALTER TABLE user " + " ADD COLUMN age INTEGER NOT NULL DEFAULT 0")
            }
        }
    }

    /**
     *  RoomDatabase提供直接访问底层数据库实现，我们通过定义抽象方法返回具体Dao,然后进行数据库增删该查的实现。
     */
    abstract fun historyDao(): HistoryDao
}
