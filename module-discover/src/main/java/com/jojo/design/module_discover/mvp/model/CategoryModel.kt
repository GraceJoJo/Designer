package com.jojo.design.module_core.mvp.model

import com.jojo.design.module_core.mvp.contract.CategoryContract
import com.jojo.design.module_core.net.NetFoundProvider
import com.jojo.design.module_discover.bean.CategoryBean
import com.jojo.design.module_discover.bean.ItemEntity
import com.jojo.design.module_discover.bean.TabEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/18 14:29 PM
 *    desc   : 分类
 */
class CategoryModel @Inject constructor() : CategoryContract.Model {
    override fun getCategoryTabs(id: String): Observable<TabEntity> = NetFoundProvider.requestService.getCategoryTabs(id)

    override fun getCategories(): Observable<List<CategoryBean>> = NetFoundProvider.requestService.getCategories()

    override fun getCategorieDetail(id: String, tabType: Int): Observable<ItemEntity> {
        var observable: Observable<ItemEntity>? = null
        when (tabType) {
            0, 1 -> observable = NetFoundProvider.requestService.getCategorieDetail(id)
            2 -> observable = NetFoundProvider.requestService.getCategoryAuthor(id)
            3 -> observable = NetFoundProvider.requestService.getCategoryPlaylist(id)

        }
        return observable!!
    }
}