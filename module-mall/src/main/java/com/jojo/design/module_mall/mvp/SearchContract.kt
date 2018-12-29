package com.jojo.design.module_mall.mvp

import com.jojo.design.common_base.dagger.mvp.BaseContract
import com.jojo.design.module_mall.bean.RecordsEntity
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/29 10:09 PM
 *    desc   : 搜索商品
 */
interface SearchContract {
    interface View : BaseContract.BaseView {
        fun getHotList(dataList: List<String>)
        fun getSearchGoods(dataBean: RecordsEntity)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getHotList()
        fun getSearchGoods(outCategoryId: String, keyword: String, page: Int)
    }

    interface Model : BaseContract.BaseModel {
        fun getHotList(): Observable<BaseHttpResponse<List<String>>>
        fun getSearchGoods(outCategoryId: String, keyword: String, page: Int): Observable<BaseHttpResponse<RecordsEntity>>
    }
}