package com.jojo.design.common_base.utils

import java.lang.reflect.ParameterizedType

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/3 4:43 PM
 *    desc   : 泛型实例化工具类 (MVP模式)-通过这个类我们可以传入一个对象通过这个对象与泛型所在位置实例化出一个泛型的对象。
 */
class ClassReflectHelper {
    companion object {
        fun <T> getT(o: Any, i: Int): T? {
            try {
                return ((o.javaClass
                        .genericSuperclass as ParameterizedType).actualTypeArguments[i] as Class<T>)
                        .newInstance()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: ClassCastException) {
                e.printStackTrace()
            }

            return null
        }

        fun forName(className: String): Class<*>? {
            try {
                return Class.forName(className)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }

            return null
        }
    }

}