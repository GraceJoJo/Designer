package com.jojo.design.module_mall.db

import android.arch.persistence.room.Room
import android.content.Context

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/2 3:49 PM
 *    desc   :
 */
class AppDatabaseHelper constructor(context: Context) {
    private var DB_NAME = "designer.db"
    val appDataBase = Room.databaseBuilder(context, AppDatabase::class.java,
            DB_NAME)
            .addMigrations(AppDatabase.MIGRATION_2_3)
            .build()!!

    companion object {
        @Volatile
        var INSTANCE: AppDatabaseHelper? = null

        fun getInstance(context: Context): AppDatabaseHelper {
            if (INSTANCE == null) {
                synchronized(AppDatabaseHelper::class) {
                    if (INSTANCE == null) {
                        INSTANCE = AppDatabaseHelper(context.applicationContext)
                    }
                }
            }
            return INSTANCE!!
        }
    }
}