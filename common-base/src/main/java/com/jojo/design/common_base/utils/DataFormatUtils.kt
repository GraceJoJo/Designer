package com.jojo.design.common_base.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/24 4:27 PM
 *    desc   : 日期转换工具类
 */
object DataFormatUtils {
    /**
     * 将毫秒转换成mm:ss
     * @param pattern
     * @param milli
     * @return
     */
    fun formatTime(milli: Long): String {
        var pattern = "mm:ss"
        val m = (milli / DateUtils.MINUTE_IN_MILLIS).toInt()
        val s = (milli / DateUtils.SECOND_IN_MILLIS % 60).toInt()
        val mm = String.format(Locale.getDefault(), "%02d", m)
        val ss = String.format(Locale.getDefault(), "%02d", s)
        return pattern.replace("mm", mm).replace("ss", ss)
    }

}