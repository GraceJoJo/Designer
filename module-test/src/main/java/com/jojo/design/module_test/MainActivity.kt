package com.jojo.design.module_test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jojo.design.module_test.dagger22.B

//Dagger2在kotlin （模拟实际MVP开发）:https://www.jianshu.com/p/11c562e2742e
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("TAG", B().getData())
    }
}
