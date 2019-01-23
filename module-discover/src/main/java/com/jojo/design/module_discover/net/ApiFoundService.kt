package com.jojo.design.module_core.net

import com.jojo.design.module_discover.bean.CategoryBean
import com.jojo.design.module_discover.bean.ItemEntity
import com.jojo.design.module_discover.bean.TabEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/7 3:42 PM
 *    desc   : 开眼视频：接口管理
 */
interface ApiFoundService {
    //公共查询参数：vc=230&deviceModel=MI
    //所有分类
    @Headers("base_url:kaiyan")
    @GET("categories/detail/tab?vc=230&deviceModel=MI")
    fun getCategoryTabs(@Query("id") id: String): Observable<TabEntity>

    //所有分类
    @Headers("base_url:kaiyan")
    @GET("categories")
    fun getCategories(): Observable<List<CategoryBean>>

    //分类详情
    @Headers("base_url:kaiyan")
    @GET("categories/detail/index?vc=230&deviceModel=MI")
    fun getCategorieDetail(@Query("id") id: String): Observable<ItemEntity>
}