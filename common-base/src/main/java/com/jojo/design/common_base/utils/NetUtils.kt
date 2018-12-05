package com.jojo.design.common_base.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.jojo.design.common_base.BaseAppliction

import java.util.Locale

/**
 * 作者：addison on 11/12/15 14:16
 * 邮箱：lsf@yonyou.com
 * 获取当前网络状态及网络类型
 */
object NetUtils {
    /**
     * 网络是否连接成功
     * @return
     */
    val isNetworkConnected: Boolean
        get() {
            val mConnectivityManager = BaseAppliction.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager.activeNetworkInfo
            return mNetworkInfo?.isAvailable ?: false
        }

    /**
     * 四种网络类型
     * WIFI: wifi
     * CMNET:中国移动互联网
     * CMWAP :中国移动梦网
     * NONE: 无网络连接
     */
    enum class NetType {
        WIFI, CMNET, CMWAP, NONE
    }

    /**
     * 当前网络是否可用
     * @param context
     * @return
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val mgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = mgr.allNetworkInfo
        if (info != null) {
            for (i in info.indices) {
                if (info[i].state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        }
        return false
    }

    /**
     * 网络是否连接成功
     * @param context
     * @return
     */
    fun isNetworkConnected(context: Context?): Boolean {
        if (context != null) {
            val mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager.activeNetworkInfo
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable
            }
        }
        return false
    }

    /**
     * wifi网络连接是否可用
     * @param context
     * @return
     */
    fun isWifiConnected(context: Context?): Boolean {
        if (context != null) {
            val mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable
            }
        }
        return false
    }

    /**
     * 移动网络连接是否可用
     * @param context
     * @return
     */
    fun isMobileConnected(context: Context?): Boolean {
        if (context != null) {
            val mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mMobileNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable
            }
        }
        return false
    }

    @SuppressLint("MissingPermission")
            /**
             * 网络类型判断
             * @param context
             * @return
             */
    fun getConnectedType(context: Context?): Int {
        if (context != null) {
            val mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager.activeNetworkInfo
            if (mNetworkInfo != null && mNetworkInfo.isAvailable) {
                return mNetworkInfo.type
            }
        }
        return -1
    }

    /**
     * 网络类型判断
     * @param context
     * @return
     */
    fun getAPNType(context: Context): NetType {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo ?: return NetType.NONE
        val nType = networkInfo.type

        if (nType == ConnectivityManager.TYPE_MOBILE) {
            return if (networkInfo.extraInfo.toLowerCase(Locale.getDefault()) == "cmnet") {
                NetType.CMNET
            } else {
                NetType.CMWAP
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            return NetType.WIFI
        }
        return NetType.NONE
    }
}
