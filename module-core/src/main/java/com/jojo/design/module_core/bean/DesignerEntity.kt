package com.jojo.design.module_core.bean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/7 3:31 PM
 *    desc   : 设计师实体
 */
data class DesignerEntity(private val id: String, var userAvatar: String, var productNum: Int, var tags: List<TagBean>, var userNick: String, val banner: String, val opTag: String, val shopName: String, val shopId: String) {
    data class TagBean(val name: String)
//    {
//        "banner": "http://xqtopic.xiangqu.com/FtgXpQe5x_0bL1TjboyspaxOxsN-?imageView2/2/w/640/q/90/format/jpg/960x645/",
//        "fansNum": null,
//        "id": 196,
//        "intro": "草木，软语品牌创始人兼设计师，毕业于师范大学，热爱传统并对中式风格极其推崇，遂投身于设计之路，以纯银天然石首饰、手工棉麻真丝服装为载体，表达对天然材质时光之美的体会和生活感悟。",
//        "isFollow": null,
//        "items": null,
//        "opTag": "品牌创始人、设计师",
//        "productNum": 41,
//        "products": null,
//        "shareUrl": null,
//        "shopId": "70wv8vnw",
//        "shopName": "软语 金银细软珠宝店",
//        "tags": [
//        {
//            "categoryId": 1,
//            "id": 3,
//            "image": "http://xqtopic.xiangqu.com/Ftg3BELXpvaRtTR30nJz5DXYKf7_?imageView2/2/w/320/q/90/format/jpg/320x320/",
//            "name": "配饰"
//        },
//        {
//            "categoryId": 2,
//            "id": 12,
//            "image": "http://xqtopic.xiangqu.com/FhNu5i_mDOUVPv-Ym3MqyhJqO0qx?imageView2/2/w/320/q/90/format/jpg/480x480/",
//            "name": "中国风"
//        }
//        ],
//        "userAvatar": "http://xquser.xiangqu.com/Fv5kXUjtDVrp8p9fa5x07OMiql9F?imageView2/2/w/120/q/90/format/jpg/480x480/",
//        "userId": 280411,
//        "userNick": "草木"
//    }
}