package com.jojo.design.module_core.mvp.contract

import com.jojo.design.common_base.dagger.mvp.BaseContract
import com.jojo.design.module_discover.bean.CategoryBean
import com.jojo.design.module_discover.bean.ItemEntity
import com.jojo.design.module_discover.bean.TabEntity
import io.reactivex.Observable

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/18 14:29 PM
 *    desc   : 分类
 */
interface CategoryContract {
    interface View : BaseContract.BaseView {
        fun getCategoryTabs(dataBean: TabEntity)
        fun getCategories(dataList: List<CategoryBean>)
        fun getCategorieDetail(dataBean: ItemEntity)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getCategoryTabs(id: String)
        fun getCategories()
        //tabType为滑动的tab类型
        fun getCategorieDetail(id: String, tabType: Int)
    }

    interface Model : BaseContract.BaseModel {
        fun getCategoryTabs(id: String): Observable<TabEntity>
        fun getCategories(): Observable<List<CategoryBean>>
        fun getCategorieDetail(id: String, tabType: Int): Observable<ItemEntity>
    }
}