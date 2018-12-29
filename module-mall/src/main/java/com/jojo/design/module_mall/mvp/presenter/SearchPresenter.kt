package com.jojo.design.module_mall.mvp.presenter

import com.jojo.design.common_base.dagger.mvp.BasePresenter
import com.jojo.design.common_base.net.RetrofitManager
import com.jojo.design.common_base.net.RxObserverListener
import com.jojo.design.module_mall.bean.RecordsEntity
import com.jojo.design.module_mall.mvp.SearchContract
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/29 5:11 PM
 *    desc   :
 */
class SearchPresenter @Inject constructor() : BasePresenter<SearchContract.View, SearchContract.Model>(), SearchContract.Presenter {
    override fun getHotList() {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getHotList(), object : RxObserverListener<List<String>>(mView) {
            override fun onSuccess(result: List<String>?) {
                mView?.getHotList(result!!)
                mView?.dismissDialogLoading()
            }
        }))
    }

    override fun getSearchGoods(outCategoryId: String, keyword: String, page: Int) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getSearchGoods(outCategoryId, keyword, page), object : RxObserverListener<RecordsEntity>(mView) {
            override fun onSuccess(result: RecordsEntity?) {
                mView?.getSearchGoods(result!!)
                mView?.dismissDialogLoading()
            }
        }))
    }
}