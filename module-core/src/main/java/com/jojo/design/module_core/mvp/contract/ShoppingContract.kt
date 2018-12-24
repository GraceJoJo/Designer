package com.jojo.design.module_core.mvp.contract

import com.jojo.design.common_base.dagger.mvp.BaseContract
import com.jojo.design.common_base.net.BaseResponse
import com.jojo.design.module_core.bean.*
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
        fun getHandPickedGoods(bean: RecordsEntity)
        fun getPersonLike(dataList: List<AllfaverEntity>)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getCategoryList()
        fun getGoodsList()
        fun getHandPickedGoods(page: String)
        fun getPersonLike()
    }

    interface Model : BaseContract.BaseModel {
        fun getCategoryList(): Observable<BaseHttpResponse<List<CategoryEntity>>>
        fun getGoodsList(): Observable<BaseResponse<List<GoodsEntity>>>
        fun getHandPickedGoods(page: String): Observable<BaseHttpResponse<RecordsEntity>>
        fun getPersonLike(): Observable<BaseHttpResponse<List<AllfaverEntity>>>
    }
}