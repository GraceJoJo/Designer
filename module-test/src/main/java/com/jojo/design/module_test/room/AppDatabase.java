package com.jojo.design.module_test.room;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/4 5:56 PM
 * desc   :
 */
//注解指定了database的表映射实体数据以及版本等信息
@Database(entities = {User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    //RoomDatabase提供直接访问底层数据库实现，我们通过定义抽象方法返回具体Dao
//然后进行数据库增删该查的实现。
    public abstract UserDao userDao();

    //数据库变动添加Migration  :[数据库操作语句大全(sql)](https://www.cnblogs.com/liuzheng-java/articles/sql.html)
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //user中添加一个新字段age
            database.execSQL("ALTER TABLE user "
                    + " ADD COLUMN age INTEGER NOT NULL DEFAULT 0");
        }
    };
}
