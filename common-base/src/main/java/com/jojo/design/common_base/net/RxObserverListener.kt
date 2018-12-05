package com.jojo.design.common_base.net

import android.util.Log

import com.jojo.design.common_base.bean.ErrorBean
import com.jojo.design.common_base.mvvm.IBaseView
import com.jojo.design.common_base.utils.ToastUtils


/**
 * Created by JoJo on 2018/4/1.
 * wechat：18510829974
 * description：网络数据监听
 */
abstract class RxObserverListener<T> protected constructor(private val mView: IBaseView?) : BaseObserverListener<T> {

    override fun onComplete() {

    }

    /**
     * 统一处理异常情况：包括没网、数据返回错误等
     *
     * @param e
     */
    override fun onError(e: Throwable) {
        val responseThrowable = RetrofitException.getResponseThrowable(e)
        Log.e("TAG", "e.code=" + responseThrowable.code + responseThrowable.message)
        val errorBean = ErrorBean()
        errorBean.msg = responseThrowable.message!!
        errorBean.code = responseThrowable.code.toString() + ""
        if (mView != null) {
            mView.showException(errorBean)
            mView.dismissDialogLoading()
            ToastUtils.makeShortToast(responseThrowable.message);
        }
    }

    override fun onBusinessError(errorBean: ErrorBean) {
        if (mView != null) {
            mView.showBusinessError(errorBean)
            mView.dismissDialogLoading()
            //            ToastUtils.makeEventToast(BaseApplication.getInstance(), errorBean.getMsg(), false);
            Log.e("TAG", "onBusinessError msg=" + errorBean.msg)
        }
    }
}
