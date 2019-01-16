package com.jojo.design.module_mall.mvp.presenter

import com.jojo.design.common_base.dagger.mvp.BasePresenter
import com.jojo.design.common_base.net.RetrofitManager
import com.jojo.design.common_base.net.RxObserverListener
import com.jojo.design.module_mall.bean.*
import com.jojo.design.module_mall.mvp.contract.GoodsContract
import com.jojo.design.module_mall.mvp.contract.SearchContract
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/1/10 17:11 PM
 *    desc   : 商品详情
 */
class GoodsPresenter @Inject constructor() : BasePresenter<GoodsContract.View, GoodsContract.Model>(), GoodsContract.Presenter {
    override fun getGoodsContent(productId: String) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getGoodsContent(productId), object : RxObserverListener<GoodsContentBean>(mView) {
            override fun onSuccess(result: GoodsContentBean?) {
                mView?.getGoodsContent(result!!)
                mView?.dismissDialogLoading()
            }
        }))
    }

    override fun getGoodsDescription(productId: String) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getGoodsDescription(productId), object : RxObserverListener<List<GoodsDesBean>>(mView) {
            override fun onSuccess(result: List<GoodsDesBean>?) {
                mView?.getGoodsDescription(result!!)
                mView?.dismissDialogLoading()
            }
        }))
    }

    override fun getGoodsCommentList(productId: String, type: Int) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getGoodsCommentList(productId, type), object : RxObserverListener<List<CommentBean>>(mView) {
            override fun onSuccess(result: List<CommentBean>?) {
                mView?.getGoodsCommentList(result!!)
                mView?.dismissDialogLoading()
            }
        }))
    }

    override fun getRevelentGoodsList(productId: String) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getRevelentGoodsList(productId), object : RxObserverListener<RevelentBean>(mView) {
            override fun onSuccess(result: RevelentBean?) {
                mView?.getRevelentGoodsList(result!!)
                mView?.dismissDialogLoading()
            }
        }))
    }
}