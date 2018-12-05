package com.will.weiyuekotlin.component

import android.content.Context
import com.jojo.design.module_test.MyApplication
import com.will.weiyuekotlin.module.ApplicationModule
import com.will.weiyuekotlin.module.HttpModule

import dagger.Component

/**
 * desc: .
 * author: Will .
 * date: 2017/9/2 .
 */
@Component(modules = [(ApplicationModule::class), (HttpModule::class)])
interface ApplicationComponent {

    val application: MyApplication

    val context: Context

}
