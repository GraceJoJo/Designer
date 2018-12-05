package com.jojo.design.common_base.mvvm

import com.jojo.design.common_base.bean.ErrorBean
import com.jojo.design.common_base.view.MultipleStatusView

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/2 2:53 PM
 *    desc   : View层接口
 */
interface IBaseView {
    /**
     * show loading message
     *
     * @param msg
     */
    fun showLoading()

    /**
     * hide loading
     */
//    fun hideLoading()

    /**
     * dialog loading
     */
    fun showDialogLoading(msg: String)

    /**
     * dismiss  dialog loading
     */
    fun dismissDialogLoading()

    /**
     * show business error :网络异常及数据错误等异常情况
     */
    fun showBusinessError(error: ErrorBean)

    fun showException(error: ErrorBean)
}
