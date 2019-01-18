package com.jojo.design.common_base.config.arouter

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/24 4:27 PM
 *    desc   : base-路由页面常量配置 注意：路径至少需要两级 {/xx/xx}
 */
class ARouterConfig {
    companion object {
        //const声明编译时常量
        const val ACT_WEBVIEW = "/base/act_commonweb"
        const val ACT_DESIGNERLIST = "/designer/act_designerlist"
        const val ACT_SEARCH = "/mall/act_search"
        const val ACT_GoodsFilter= "/mall/act_goodsfilter"
        const val ACT_GoodsDetail= "/mall/act_goodsdetail"
        const val ACT_Category= "/found/act_category"
    }

}