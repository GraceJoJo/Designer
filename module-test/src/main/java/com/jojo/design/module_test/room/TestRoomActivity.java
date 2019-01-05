package com.jojo.design.module_test.room;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jojo.design.common_base.utils.ToastUtils;
import com.jojo.design.module_test.MainActivity;
import com.jojo.design.module_test.R;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/4 5:39 PM
 * desc   : 数据库 Room 测试
 */
public class TestRoomActivity extends AppCompatActivity {
    private UserDao mUserDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_room_activity);

        //得到AppDatabase 对象
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "roomDemo-database")
                //下面注释表示允许主线程进行数据库操作，但是不推荐这样做。
                //他可能造成主线程lock以及anr
                //所以我们的操作都是在新线程完成的
                // .allowMainThreadQueries()
                .addMigrations(AppDatabase.MIGRATION_1_2)
                .build();
        //得到userDao对象
        mUserDao = db.userDao();
    }

    public void insert(View view) {
        ToastUtils.Companion.makeShortToast("插入");
        //防止UI线程阻塞以及ANR,所有的数据库操作，推荐开启新的线程访问。
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User(1, "娟娟", "jojo", 18);
                Long index = mUserDao.insert(user);
                Log.e("TAG", "insert success index=" + index);
            }
        }).start();


    }

    public void queryItem(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = mUserDao.findByUid(1);
                Log.e("TAG", "query success" + user.firstName);
            }
        }).start();

    }

    public void queryAll(View view) {

    }

    public void deleteItem(View view) {

    }

    public void deleteAll(View view) {

    }
}
