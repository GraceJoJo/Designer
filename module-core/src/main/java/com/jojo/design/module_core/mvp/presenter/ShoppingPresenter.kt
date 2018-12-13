package com.jojo.design.module_core.mvp.presenter

import com.jojo.design.common_base.dagger.mvp.BasePresenter
import com.jojo.design.common_base.net.RetrofitManager
import com.jojo.design.common_base.net.RxObserverListener
import com.jojo.design.module_core.bean.CategoryEntity
import com.jojo.design.module_core.bean.DesignerEntity
import com.jojo.design.module_core.bean.GoodsEntity
import com.jojo.design.module_core.bean.TagCategoryEntity
import com.jojo.design.module_core.mvp.contract.DesignerContract
import com.jojo.design.module_core.mvp.contract.ShoppingContract
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/12 10:09 PM
 *    desc   : 逛
 */
class ShoppingPresenter @Inject constructor() : BasePresenter<ShoppingContract.View, ShoppingContract.Model>(), ShoppingContract.Presenter {
    override fun getCategoryList() {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getCategoryList(), object : RxObserverListener<List<CategoryEntity>>(mView) {
            override fun onSuccess(result: List<CategoryEntity>?) {
                mView?.getCategoryList(result!!)
                mView?.dismissDialogLoading()
            }
        }))
    }

    override fun getGoodsList() {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doRequestOther(mModel!!.getGoodsList(), object : RxObserverListener<List<GoodsEntity>>(mView) {
            override fun onSuccess(result: List<GoodsEntity>?) {
                mView?.getGoodsList(result!!)
                mView?.dismissDialogLoading()
            }
        }))
    }
}