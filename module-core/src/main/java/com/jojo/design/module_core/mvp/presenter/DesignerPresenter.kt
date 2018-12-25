package com.jojo.design.module_core.mvp.presenter

import android.util.Log
import com.jojo.design.common_base.dagger.mvp.BasePresenter
import com.jojo.design.common_base.net.RetrofitManager
import com.jojo.design.common_base.net.RxObserverListener
import com.jojo.design.module_core.bean.DesignerEntity
import com.jojo.design.module_core.bean.TagCategoryEntity
import com.jojo.design.module_core.mvp.contract.DesignerContract
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/7 10:09 PM
 *    desc   : 设计师 presenter
 */
class DesignerPresenter @Inject constructor() : BasePresenter<DesignerContract.View, DesignerContract.Model>(), DesignerContract.Presenter {
    override fun getDesinerList(tagCategoryId: String, tagId: String) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doRequestOther(mModel!!.getDesinerList(tagCategoryId, tagId), object : RxObserverListener<List<DesignerEntity>>(mView) {
            override fun onSuccess(result: List<DesignerEntity>?) {
                mView?.getDesinerList(result!!)
                mView?.dismissDialogLoading()
            }
        }))
    }

    override fun getRecommendDesigner() {
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getRecommendDesigner(), object : RxObserverListener<DesignerEntity>(mView) {
            override fun onSuccess(result: DesignerEntity?) {
                mView?.getRecommendDesigner(result!!)
            }

        }))
    }

    override fun getDesignerTypeList() {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getDesignerTypeList(), object : RxObserverListener<List<TagCategoryEntity>>(mView) {
            override fun onSuccess(result: List<TagCategoryEntity>?) {
                mView?.getDesignerTypeList(result!!)
                mView?.dismissDialogLoading()
            }

        }))
    }
}