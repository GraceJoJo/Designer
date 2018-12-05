package com.jojo.design.common_base.utils
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.BaseAppliction.Companion.context
import com.jojo.design.common_base.R

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/2 3:16 PM
 *    desc   : Toast工具类
 */
class ToastUtils {

    companion object {
        private var toast: Toast? = null
        /**
         * 显示toast
         *
         * @param context
         * @param text
         * @param isLongToast
         */
        fun makeEventToast( text: String?,
                            isLongToast: Boolean) {

            val v = LayoutInflater.from(BaseAppliction.context).inflate(R.layout.toast_view, null)
            val textView = v.findViewById<TextView>(R.id.text)
            textView.text = text

            //每次创建Toast时先做一下判断
            //如果前面已经有Toast在显示，则只是更新toast内容，而不再创建，提升用户体验
            if (toast != null) {
                textView.text = text
            } else {
                toast = Toast.makeText(BaseAppliction.context, text, if (isLongToast) Toast.LENGTH_LONG else Toast.LENGTH_SHORT)
            }

            toast!!.view = v
            toast!!.show()
        }

        /**
         * 显示toast
         *
         * @param text
         */
        fun makeShortToast(text: String?) {
            if (null == text)
                return

            val v = LayoutInflater.from(BaseAppliction.context).inflate(R.layout.toast_view, null)
            val textView = v.findViewById<TextView>(R.id.text)
            textView.text = text

            //每次创建Toast时先做一下判断
            //如果前面已经有Toast在显示，则只是更新toast内容，而不再创建，提升用户体验
            if (toast != null) {
                textView.text = text
            } else {
                toast = Toast.makeText(BaseAppliction.context, text, Toast.LENGTH_SHORT)
            }
            toast!!.view = v
            toast!!.show()
        }
    }

}