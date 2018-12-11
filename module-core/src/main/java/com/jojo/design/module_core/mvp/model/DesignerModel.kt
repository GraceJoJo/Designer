package com.jojo.design.module_core.mvp.model

import com.jojo.design.module_core.net.NetSeriviceProvider
import com.jojo.design.module_core.bean.DesignerEntity
import com.jojo.design.module_core.bean.TagCategoryEntity
import com.jojo.design.module_core.mvp.contract.DesignerContract
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/7 10:09 PM
 *    desc   : 设计师 model
 */
class DesignerModel @Inject constructor() : DesignerContract.Model {
    /**
     * 获取推荐设计师
     */
    override fun getRecommendDesigner(): Observable<BaseHttpResponse<DesignerEntity>> = NetSeriviceProvider.requestService.getRecommendDesigner()

    /**
     * 获取涉及标签类型列表
     */
    override fun getDesignerTypeList(): Observable<BaseHttpResponse<List<TagCategoryEntity>>> = NetSeriviceProvider.requestService.getDesignerTypeList()
}