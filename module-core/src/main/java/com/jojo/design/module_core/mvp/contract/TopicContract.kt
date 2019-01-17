package com.jojo.design.module_core.mvp.contract

import com.jojo.design.common_base.dagger.mvp.BaseContract
import com.jojo.design.module_core.bean.TopicBean
import com.jojo.design.module_core.bean.TopicDetailEntity
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/17 2:53 PM
 *    desc   : 专题
 */
interface TopicContract {
    interface View : BaseContract.BaseView {
        fun getTopics(dataList: List<TopicBean>)
        fun getTopicDetail(dataBean: TopicDetailEntity)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getTopics(id: String)
        fun getTopicDetail(id: String)
    }

    interface Model : BaseContract.BaseModel {
        fun getTopics(id: String): Observable<BaseHttpResponse<List<TopicBean>>>
        fun getTopicDetail(id: String): Observable<BaseHttpResponse<TopicDetailEntity>>
    }
}