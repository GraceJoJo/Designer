package com.jojo.design

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import butterknife.BindView
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv.setOnClickListener({
            //发起路由跳转
            ARouter.getInstance().build("/mall/act_mall").navigation(this, object : NavigationCallback {
                override fun onFound(postcard: Postcard) {
                    Log.e("TAG", "找到了")
                }

                override fun onLost(postcard: Postcard) {
                    Log.e("TAG", "找不到了")
                }

                override fun onArrival(postcard: Postcard) {
                    Log.e("TAG", "跳转了")
                }

                override fun onInterrupt(postcard: Postcard) {
                    Log.e("TAG", "拦截了")
                }
            })
        })
    }
}
