package com.zongxueguan.naochanle_android.retrofitrx

import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/2 1:49 PM
 *    desc   : Api接口配置
 */
interface ApiService {

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
//    @GET("designer2/recommend/operate")
//    fun getRecommendDesigner(): Observable<BaseHttpResponse<List<TagCategoryEntity>>>
////    获取设计师分类
//    @GET("designer2/tag/index")
//    fun getDesignerTypeList(): Observable<BaseHttpResponse<List<TagCategoryEntity>>>
}