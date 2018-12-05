package com.jojo.design.common_base.mvvm

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/3 2:59 PM
 *    desc   : Observables 和 Subscribers管理,防止内存泄漏
 */
class RxManager {
    private val compositeDisposable = CompositeDisposable()

    /**
     * 添加observer
     * @param observer
     */
    fun addObserver(observer: DisposableObserver<*>?) {
        if (observer != null) {
            compositeDisposable.add(observer)
        }
    }

    fun clear() {
        compositeDisposable.dispose()
    }
}