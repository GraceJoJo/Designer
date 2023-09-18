package com.jojo.design.module_test.room;

import androidx.room.Room;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jojo.design.common_base.utils.ToastUtils;
import com.jojo.design.module_test.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


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
//                .addMigrations(AppDatabase.MIGRATION_1_2)
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
                User user = new User(2, "娟娟", "jojo", 18);
                Long index = mUserDao.insert(user);
                Log.e("TAG", "insert success index=" + index);
            }
        }).start();


    }

    public void queryItem(View view) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                User user = mUserDao.findByUid(1);
//                Log.e("TAG", "query success" + user.firstName);
//            }
//        }).start();
        mUserDao.findByUid(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        String msg = "query succes, index is " + user.toString();
                        Log.e("TAG", msg);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("TAG", throwable.getMessage().toString());
                    }
                });

    }

    public void updateItem(View view) {
        final User user = new User(2, "娟娟小花花", "jojo", 20);
        new Thread(new Runnable() {
            @Override
            public void run() {

                //如果使用Flowable，那么每当Flowable包裹的对象改变时，Flowable将自动发射，也就是自动执行accept回调。
                int index = mUserDao.update(user);
                Log.e("TAG", "insert success index=" + index); //同时会执行 findByUid(2)的accept回调
            }
        }).start();
//        mUserDao.update(user)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        String msg = "update succes, index is " + integer.toString();
//                        Log.e("TAG", msg);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.e("TAG", throwable.getMessage().toString());
//                    }
//                });

    }
    public void queryAll(View view) {

    }

    public void deleteItem(View view) {

    }

    public void deleteAll(View view) {

    }
}
