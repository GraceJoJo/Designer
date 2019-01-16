package com.jojo.design.module_mall.net

import com.jojo.design.module_mall.bean.*
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


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

    //点击分类标签搜索商品  key=05bddc6fa2cc21c57ea1ae11de699bcd&outCategoryId=2&page=0&sort=0&t=1546073569203&tagid=0&version=2.3.04
    @GET("search/list")
    fun getSearchGoods(@Query("outCategoryId") outCategoryId: String, @Query("keyword") keyword: String, @Query("page") page: Int, @Query("sort") sort: Int, @QueryMap queryParams: Map<String, String>): Observable<BaseHttpResponse<RecordsEntity>>

    //点击筛选栏->选择分类  key=4de264770bd2568b938c832f96f345c1&outCategoryId=1&t=1547004222303&version=2.3.04
    @GET("search/list/filtrate/category")
    fun getCategoryList(@Query("outCategoryId") outCategoryId: String, @Query("keyword") keyword: String): Observable<BaseHttpResponse<List<CategoryBean>>>

    //点击筛选栏->右侧筛选  key=3dfe6888b9b99ce6648d0c4f59575e02&outCategoryId=1&t=1547004464740&version=2.3.04
    @GET("search/list/filtrate/others")
    fun getFilterData(@Query("outCategoryId") outCategoryId: String): Observable<BaseHttpResponse<FilterBean>>

    /**
     * 商品详情页
     */
    //商品详情内容
    @GET("product/content")
    fun getGoodsContent(@Query("productId") productId: String): Observable<BaseHttpResponse<GoodsContentBean>>

    //商品详情-商品描述
    @GET("product/chips")
    fun getGoodsDescription(@Query("productId") productId: String): Observable<BaseHttpResponse<List<GoodsDesBean>>>

    //商品详情-用户评论列表
    @GET("action/comment/list")
    fun getGoodsCommentList(@Query("objectId") productId: String, @Query("type") type: Int): Observable<BaseHttpResponse<List<CommentBean>>>

    //商品详情-底部相似推荐
    @GET("ouer/product/revelentList")
    fun getRevelentGoodsList(@Query("productId") productId: String): Observable<BaseHttpResponse<RevelentBean>>
}