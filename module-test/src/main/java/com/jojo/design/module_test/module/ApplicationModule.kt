package com.will.weiyuekotlin.module

import android.content.Context
import com.jojo.design.module_test.MyApplication

import dagger.Module
import dagger.Provides

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/11/30 10:25 AM
 *    desc   : Application初始化
 */
@Module
class ApplicationModule(private val mContext: Context) {

    @Provides
    internal fun provideApplication(): MyApplication {
        return mContext.applicationContext as MyApplication
    }

    @Provides
    internal fun provideContext(): Context {
        return mContext
    }
}
