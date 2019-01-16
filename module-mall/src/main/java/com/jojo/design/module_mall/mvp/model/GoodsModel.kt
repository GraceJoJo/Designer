package com.jojo.design.module_mall.mvp.model

import com.jojo.design.module_mall.bean.*
import com.jojo.design.module_mall.mvp.contract.GoodsContract
import com.jojo.design.module_mall.mvp.contract.SearchContract
import com.jojo.design.module_mall.net.NetMallProvider
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/1/10 17:11 PM
 *    desc   : 商品详情
 */
class GoodsModel @Inject constructor() : GoodsContract.Model {
    override fun getGoodsContent(productId: String): Observable<BaseHttpResponse<GoodsContentBean>> = NetMallProvider.requestService.getGoodsContent(productId)

    override fun getGoodsDescription(productId: String): Observable<BaseHttpResponse<List<GoodsDesBean>>> = NetMallProvider.requestService.getGoodsDescription(productId)

    override fun getGoodsCommentList(productId: String, type: Int): Observable<BaseHttpResponse<List<CommentBean>>> = NetMallProvider.requestService.getGoodsCommentList(productId, type)

    override fun getRevelentGoodsList(productId: String): Observable<BaseHttpResponse<RevelentBean>> = NetMallProvider.requestService.getRevelentGoodsList(productId)

}