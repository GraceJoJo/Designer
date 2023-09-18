package com.jojo.design.common_ui.dialog

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.jojo.design.common_ui.LoadingCircleView
import com.jojo.design.common_ui.R

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/3 11:34 AM
 *    desc   : 加载Dialog
 */
class LoadingDialog constructor(context: Context) {

    private var mLoadingView: LoadingCircleView? = null
    private var mLoadingDialog: Dialog? = null
    private var context: Context? = null
    private var msg = "加载中···"
    private var cancelable = true
    private var isShow: Boolean = false

    init {
        this.context = context
    }

    /**
     * 设置提示信息
     */
    fun setTitleText(msg: String): LoadingDialog {
        this.msg = msg
        return this
    }

    /**
     * 返回键是否可用
     */
    fun setCancelable(cancelable: Boolean): LoadingDialog {
        this.cancelable = cancelable
        return this
    }

    fun show() {
        if (!isShow) {
            val view = View.inflate(context, R.layout.dialog_loading, null)
            // 获取整个布局
            val layout = view.findViewById<LinearLayout>(R.id.dialog_view)
            // 页面中的LoadingView
            mLoadingView = view.findViewById(R.id.loading_view)
            // 页面中显示文本
            val loadingText = view.findViewById<TextView>(R.id.loading_text)
            // 显示文本
            loadingText.setText(msg)
            // 创建自定义样式的Dialog
            mLoadingDialog = Dialog(context!!, R.style.LoadingDialog)
            // 设置返回键无效
            mLoadingDialog!!.setCancelable(cancelable)
            mLoadingDialog!!.setCanceledOnTouchOutside(false)

            mLoadingDialog!!.setContentView(layout, LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT))
            if (context != null) {
                mLoadingDialog!!.show()
                isShow = true
            }
        }
    }

    fun dismiss() {
        if (mLoadingDialog != null && isShow) {
            mLoadingView!!.stopAnim()
            mLoadingDialog!!.dismiss()
            mLoadingDialog = null
            isShow = false
        }
    }

    fun isShowing(): Boolean {
        return isShow
    }
}