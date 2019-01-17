package com.jojo.design.module_core.mvp.presenter

import com.jojo.design.common_base.dagger.mvp.BasePresenter
import com.jojo.design.common_base.net.RetrofitManager
import com.jojo.design.common_base.net.RxObserverListener
import com.jojo.design.module_core.bean.DesignerEntity
import com.jojo.design.module_core.bean.TagCategoryEntity
import com.jojo.design.module_core.bean.TopicBean
import com.jojo.design.module_core.bean.TopicDetailEntity
import com.jojo.design.module_core.mvp.contract.DesignerContract
import com.jojo.design.module_core.mvp.contract.TopicContract
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/17 2:53 PM
 *    desc   : 专题
 */
class TopicPresenter @Inject constructor() : BasePresenter<TopicContract.View, TopicContract.Model>(), TopicContract.Presenter {
    override fun getTopics(id: String) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getTopics(id), object : RxObserverListener<List<TopicBean>>(mView) {
            override fun onSuccess(result: List<TopicBean>?) {
                mView?.getTopics(result!!)
                mView?.dismissDialogLoading()
            }

        }))
    }

    override fun getTopicDetail(id: String) {
        mView?.showDialogLoading("")
        rxManager?.addObserver(RetrofitManager.doCommonRequest(mModel!!.getTopicDetail(id), object : RxObserverListener<TopicDetailEntity>(mView) {
            override fun onSuccess(result: TopicDetailEntity?) {
                mView?.getTopicDetail(result!!)
                mView?.dismissDialogLoading()
            }

        }))
    }
}