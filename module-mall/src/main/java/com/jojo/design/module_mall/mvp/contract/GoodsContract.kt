package com.jojo.design.module_mall.mvp.contract

import com.jojo.design.common_base.dagger.mvp.BaseContract
import com.jojo.design.module_mall.bean.*
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/1/10 17:11 PM
 *    desc   : 商品详情
 */
interface GoodsContract {
    interface View : BaseContract.BaseView {
        fun getGoodsContent(dataBean: GoodsContentBean)
        fun getGoodsDescription(dataList: List<GoodsDesBean>)
        fun getGoodsCommentList(dataList: List<CommentBean>)
        fun getRevelentGoodsList(dataBean: RevelentBean)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getGoodsContent(productId: String)
        fun getGoodsDescription(productId: String)
        fun getGoodsCommentList(productId: String, type: Int)
        fun getRevelentGoodsList(productId: String)
    }

    interface Model : BaseContract.BaseModel {
        fun getGoodsContent(productId: String): Observable<BaseHttpResponse<GoodsContentBean>>
        fun getGoodsDescription(productId: String): Observable<BaseHttpResponse<List<GoodsDesBean>>>
        fun getGoodsCommentList(productId: String, type: Int): Observable<BaseHttpResponse<List<CommentBean>>>
        fun getRevelentGoodsList(productId: String): Observable<BaseHttpResponse<RevelentBean>>
    }
}