package com.jojo.design.module_core.mvp.contract

import com.jojo.design.common_base.dagger.mvp.BaseContract
import com.jojo.design.common_base.net.BaseResponse
import com.jojo.design.module_core.bean.CategoryEntity
import com.jojo.design.module_core.bean.DesignerEntity
import com.jojo.design.module_core.bean.GoodsEntity
import com.jojo.design.module_core.bean.TagCategoryEntity
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/12 10:09 PM
 *    desc   : 逛
 */
interface ShoppingContract {
    interface View : BaseContract.BaseView {
        fun getCategoryList(dataList: List<CategoryEntity>)
        fun getGoodsList(dataList: List<GoodsEntity>)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getCategoryList()
        fun getGoodsList()
    }

    interface Model : BaseContract.BaseModel {
        fun getCategoryList(): Observable<BaseHttpResponse<List<CategoryEntity>>>
        fun getGoodsList(): Observable<BaseResponse<List<GoodsEntity>>>
    }
}