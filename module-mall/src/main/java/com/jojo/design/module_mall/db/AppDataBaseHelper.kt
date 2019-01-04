package com.jojo.design.module_mall.db

import android.arch.persistence.room.Room
import android.content.Context

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/2 3:49 PM
 *    desc   :
 */
class AppDataBaseHelper constructor(context: Context) {
    private var DB_NAME = "designer.db"
    val appDataBase = Room.databaseBuilder(context, AppDataBase::class.java,
            DB_NAME).build()!!

    companion object {
        @Volatile
        var INSTANCE: AppDataBaseHelper? = null

        fun getInstance(context: Context): AppDataBaseHelper {
            if (INSTANCE == null) {
                synchronized(AppDataBaseHelper::class) {
                    if (INSTANCE == null) {
                        INSTANCE = AppDataBaseHelper(context.applicationContext)
                    }
                }
            }
            return INSTANCE!!
        }
    }
}