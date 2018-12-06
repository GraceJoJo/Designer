package com.jojo.design.common_base.config.constants

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/2 6:46 PM
 *    desc   : 广播常量
 */
class BroadCastConstant {
    companion object {
        @JvmField val LOGOUT = 1001
        @JvmField val LOGIN_SUCCESS = 1002
        //广播相对地址
        @JvmField val BROADCASE_ADDRESS = ".broadcast"
        @JvmField val BROADCASE_INTENT = ".intent"
    }
}