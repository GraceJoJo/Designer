package com.jojo.design.module_core.mvp.contract

import com.jojo.design.common_base.dagger.mvp.BaseContract
import com.jojo.design.module_discover.bean.CategoryBean
import com.jojo.design.module_discover.bean.ItemEntity
import io.reactivex.Observable

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/18 14:29 PM
 *    desc   : 分类
 */
interface CategoryContract {
    interface View : BaseContract.BaseView {
        fun getCategories(dataList: List<CategoryBean>)
        fun getCategorieDetail(dataBean: ItemEntity)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getCategories()
        fun getCategorieDetail(id:String)
    }

    interface Model : BaseContract.BaseModel {
        fun getCategories(): Observable<List<CategoryBean>>
        fun getCategorieDetail(id:String):Observable<ItemEntity>
    }
}