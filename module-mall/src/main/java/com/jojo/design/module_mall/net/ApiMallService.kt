package com.jojo.design.module_core.net

import com.jojo.design.module_mall.bean.RecordsEntity
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/29 3:42 PM
 *    desc   : 接口管理
 */
interface ApiMallService {

    //获取热门搜索
    @GET("search/recowords")
    fun getHotList(): Observable<BaseHttpResponse<List<String>>>

    //点击分类标签搜索  key=05bddc6fa2cc21c57ea1ae11de699bcd&outCategoryId=2&page=0&sort=0&t=1546073569203&tagid=0&version=2.3.04
    @GET("search/list")
    fun getSearchGoods(@Query("outCategoryId") outCategoryId: String, @Query("keyword") keyword: String, @Query("page") page: Int): Observable<BaseHttpResponse<RecordsEntity>>
}