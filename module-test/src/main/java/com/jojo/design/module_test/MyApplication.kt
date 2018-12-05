package com.jojo.design.module_test

import android.app.Activity
import android.app.Application
import com.will.weiyuekotlin.component.ApplicationComponent
import com.will.weiyuekotlin.component.DaggerApplicationComponent
import com.will.weiyuekotlin.module.ApplicationModule
import com.will.weiyuekotlin.module.HttpModule
import kotlin.properties.Delegates

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/11/30 9:16 PM
 *    desc   :
 */
class MyApplication : Application() {

    companion object {
        var instance: MyApplication by Delegates.notNull()
        var mApplicationComponent: ApplicationComponent by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .httpModule(HttpModule())
                .build()

    }
}