package com.jojo.design.module_core.net

import com.jojo.design.module_core.bean.DesignerEntity
import com.jojo.design.module_core.bean.TagCategoryEntity
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/7 3:42 PM
 *    desc   :
 */
interface ApiCoreService {
    /**
     * 文件上传
     */
    @POST("upload/")
    fun uploadFile(@Body requestBody: RequestBody): Observable<String>

//    //获取商品列表
//    @GET("shopping/banner/list")
//    abstract fun getGoodsList(): Observable<BannerContentEntity>
//
//    //获取设计师列表
//    @GET("designer2/list")
//    abstract fun getDesignerList(): Observable<DesignBean>

    //    获取顶部推荐设计师
    @GET("designer2/recommend/operate")
    fun getRecommendDesigner(): Observable<BaseHttpResponse<DesignerEntity>>

    //    获取设计师分类
    @GET("designer2/tag/index")
    fun getDesignerTypeList(): Observable<BaseHttpResponse<List<TagCategoryEntity>>>
}