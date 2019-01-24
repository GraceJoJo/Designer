package com.jojo.design.module_core.mvp.presenter

import android.util.Log
import com.jojo.design.common_base.dagger.mvp.BasePresenter
import com.jojo.design.common_base.net.RetrofitManager
import com.jojo.design.common_base.net.RxObserverListener
import com.jojo.design.module_core.mvp.contract.CategoryContract
import com.jojo.design.module_discover.bean.CategoryBean
import com.jojo.design.module_discover.bean.ItemEntity
import com.jojo.design.module_discover.bean.TabEntity
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/18 14:29 PM
 *    desc   : 分类
 */
class CategoryPresenter @Inject constructor() : BasePresenter<CategoryContract.View, CategoryContract.Model>(), CategoryContract.Presenter {
    override fun getCategoryTabs(id: String) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doRequest(mModel!!.getCategoryTabs(id), object : RxObserverListener<TabEntity>(mView) {
            override fun onSuccess(result: TabEntity?) {
                mView?.getCategoryTabs(result!!)
                mView?.dismissDialogLoading()
            }

        }))
    }

    override fun getCategories() {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doRequest(mModel!!.getCategories(), object : RxObserverListener<List<CategoryBean>>(mView) {
            override fun onSuccess(result: List<CategoryBean>?) {
                mView?.getCategories(result!!)
                mView?.dismissDialogLoading()
            }

        }))
    }

    override fun getCategorieDetail(id: String, tabType: Int) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doRequest(mModel!!.getCategorieDetail(id, tabType), object : RxObserverListener<ItemEntity>(mView) {
            override fun onSuccess(result: ItemEntity?) {
                mView?.getCategorieDetail(result!!)
                mView?.dismissDialogLoading()
            }

        }))
    }
}