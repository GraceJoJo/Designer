package com.jojo.design.module_core.mvp.model

import com.jojo.design.module_core.bean.TopicBean
import com.jojo.design.module_core.bean.TopicDetailEntity
import com.jojo.design.module_core.mvp.contract.TopicContract
import com.jojo.design.module_core.net.NetCoreProvider
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/17 2:53 PM
 *    desc   : 专题
 */
class TopicModel @Inject constructor() : TopicContract.Model {
    override fun getTopics(id: String): Observable<BaseHttpResponse<List<TopicBean>>> = NetCoreProvider.requestService.getTopics(id)

    override fun getTopicDetail(id: String): Observable<BaseHttpResponse<TopicDetailEntity>> = NetCoreProvider.requestService.getTopicDetail(id)

}