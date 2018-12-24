package com.jojo.design.module_core.mvp.model

import com.jojo.design.common_base.net.BaseResponse
import com.jojo.design.module_core.bean.*
import com.jojo.design.module_core.mvp.contract.DesignerContract
import com.jojo.design.module_core.mvp.contract.ShoppingContract
import com.jojo.design.module_core.net.NetSeriviceProvider
import com.smart.novel.net.BaseHttpResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/12 10:09 PM
 *    desc   : 逛
 */
class ShoppingModel @Inject constructor() : ShoppingContract.Model {
    //商品分类
    override fun getCategoryList(): Observable<BaseHttpResponse<List<CategoryEntity>>> = NetSeriviceProvider.requestService.getCategoryList()
    //商品列表
    override fun getGoodsList(): Observable<BaseResponse<List<GoodsEntity>>> = NetSeriviceProvider.requestService.getGoodsList()
    //精选
    override fun getHandPickedGoods(page: String): Observable<BaseHttpResponse<RecordsEntity>> = NetSeriviceProvider.requestService.getHandPickedGoods(page)
    //大家喜欢
    override fun getPersonLike(): Observable<BaseHttpResponse<List<AllfaverEntity>>> = NetSeriviceProvider.requestService.getPersonLike()
}