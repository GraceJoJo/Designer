package com.jojo.design.module_core.mvp.contract

import com.jojo.design.common_base.dagger.mvp.BaseContract
import com.jojo.design.common_base.net.BaseResponse
import com.jojo.design.module_core.bean.DesignerEntity
import com.jojo.design.module_core.bean.TagCategoryEntity
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/7 10:09 PM
 *    desc   : 设计师
 */
interface DesignerContract {
    interface View : BaseContract.BaseView {
        fun getDesignerTypeList(dataList: List<TagCategoryEntity>)
        fun getRecommendDesigner(topDesigner: DesignerEntity)
        fun getDesinerList(dataList: List<DesignerEntity>)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getDesignerTypeList()
        fun getRecommendDesigner()
        fun getDesinerList(tagCategoryId: String, tagId: String)
    }

    interface Model : BaseContract.BaseModel {
        fun getDesignerTypeList(): Observable<BaseHttpResponse<List<TagCategoryEntity>>>
        fun getRecommendDesigner(): Observable<BaseHttpResponse<DesignerEntity>>
        fun getDesinerList(tagCategoryId: String, tagId: String): Observable<BaseResponse<List<DesignerEntity>>>
    }
}