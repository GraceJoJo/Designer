package com.jojo.design.common_base.net;


import com.jojo.design.common_base.bean.ErrorBean;
/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/2 1:49 PM
 *    desc   : description：自定义接口，处理联网请求结果的回调
 */
public interface BaseObserverListener<T> {
    void onSuccess(T result);
    void onComplete();
    void onError(Throwable e);
    void onBusinessError(ErrorBean errorBean);
}
