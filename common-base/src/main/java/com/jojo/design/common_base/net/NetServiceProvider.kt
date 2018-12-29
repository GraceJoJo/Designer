package com.jojo.design.common_base.net

import okhttp3.internal.Internal.instance
import java.lang.reflect.ParameterizedType
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/29 6:44 PM
 *    desc   : Base NetServiceProvider通过每个module传入具体的业务接口类型服务实例化ApiService
 */
class NetServiceProvider<T> {

    fun getNetService(): T {
        val tClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
        return RetrofitManager.getRetrofit().create<T>(tClass)

    }
}