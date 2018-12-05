package com.jojo.design.common_base

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.will.weiyuekotlin.component.ApplicationComponent
import com.will.weiyuekotlin.component.DaggerApplicationComponent
import com.will.weiyuekotlin.module.ApplicationModule
import com.will.weiyuekotlin.module.HttpModule
import retrofit2.Retrofit
import kotlin.properties.Delegates

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/11/29 3:25 PM
 *    desc   :
 */
open class BaseAppliction : Application() {
    ////用companion object包裹，实现java中static的效果,包裹的方法或者变量都是static的
    companion object {
        lateinit var context: Context
        var mApplicationComponent: ApplicationComponent by Delegates.notNull()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        //MultiDex分包方法 必须最先初始化
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initARouter()
        initDagger()
    }

    private fun initDagger() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .httpModule(HttpModule())
                .build()
        Log.e("TAG", "mApplicationComponent=" + mApplicationComponent)
    }

    /**
     * 初始化路由
     */
    private fun initARouter() {
        // 初始化 ARouter
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }

}