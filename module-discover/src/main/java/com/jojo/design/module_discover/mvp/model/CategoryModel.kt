package com.jojo.design.module_core.mvp.model

import com.jojo.design.module_core.mvp.contract.CategoryContract
import com.jojo.design.module_core.net.NetFoundProvider
import com.jojo.design.module_discover.bean.CategoryBean
import com.jojo.design.module_discover.bean.ItemEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/18 14:29 PM
 *    desc   : 分类
 */
class CategoryModel @Inject constructor() : CategoryContract.Model {
    override fun getCategories(): Observable<List<CategoryBean>> =NetFoundProvider.requestService.getCategories()

    override fun getCategorieDetail(id: String): Observable<ItemEntity> = NetFoundProvider.requestService.getCategorieDetail(id)
}