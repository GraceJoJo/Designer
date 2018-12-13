package com.jojo.design.module_core.bean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/12 2:22 PM
 *    desc   : 逛（列表商品的实体）
 */
data class GoodsEntity(val id: Int, val h5Url: String, val image: String, val items: List<ItemProductBean>, val name: String, var title: String) {
    data class ItemProductBean(val brandName: String, val image: String, val keyword: String, val price: String)
//        "description": "把黑的色彩调配到极致境界，简洁而神秘，一次次唤醒暗黑一族叛逆精神！",
//        "h5Url": "http://www.xiangqu.com/shopping/banner/index?id=675",
//        "id": 675,
//        "image": "http://xqproduct.xiangqu.com/FlMKrMDPKD6u0zgxnYZpiSQBu4iL?imageView2/2/w/800/q/90/format/jpg/960x615/",
//        "items": [
//        {
//            "brandName": "暗嘿骑士",
//            "id": 3120815,
//            "image": "http://xqproduct.xiangqu.com/Fjx2TrEDNjheomeDgdCr0H2kJiYY?imageView2/2/w/240/q/90/format/jpg/800x800/",
//            "keyword": "立领棉衣 • 两色",
//            "originalPrice": 399,
//            "price": 351.12
//        }
//        ]
}