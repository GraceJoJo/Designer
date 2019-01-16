package com.jojo.design.module_mall.bean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/10 4:22 PM
 *    desc   : 商品详情内容
 */
data class GoodsContentBean(var productId: String, var image: String, var imgsUrlList: List<String>, var title: String, var favNum: String, var brandStory: String, var price: String, var productDiscountTxt: String,
                            var postage: String, var guarantees: List<GuaranteeBean>, var avaPath: String,
                            var productUser: String, var brandIcon: String, var brand: String,
                            var platFormWeixin: PlatFormWeixinBean) {
    data class GuaranteeBean(var title: String)
    data class PlatFormWeixinBean(var desc: String)
//    {
//        "activities": [],
//        "avaPath": "http://xquser.xiangqu.com/FqHThWK9xsDVk6-UNETPk3oU9WY1?imageView2/2/w/64/q/90/format/jpg/1080x1080/",
//        "brand": "北望",
//        "brandIcon": "http://xqproduct.xiangqu.com/Fgbg8iZZrPy1D7R5qIdNXMshE_JV",
//        "brandId": 761300,
//        "brandStory": "中原北望气如山",
//        "bulletin": "",
//        "categoryId": 19,
//        "commentList": null,
//        "commentNum": 2,
//        "delayDays": 0,
//        "description": "",
//        "domainUrl": "淘宝网",
//        "endTime": null,
//        "favNum": 47,
//        "fromType": 1,
//        "guarantees": [
//        {
//            "enable": false,
//            "helpUrl": "http://www.xiangqu.com/agreement/guarantee#A1",
//            "icon": "http://xqres.xiangqu.com/icn_service2@2x.png",
//            "key": 1,
//            "pcIcon": "http://xqres.xiangqu.com/web_7.png",
//            "title": "7天无理由退货"
//        },
//        {
//            "enable": false,
//            "helpUrl": "http://www.xiangqu.com/agreement/guarantee#A2",
//            "icon": "http://xqres.xiangqu.com/icn_service2@2x.png",
//            "key": 4,
//            "pcIcon": "http://xqres.xiangqu.com/web_48h.png",
//            "title": "48小时发货"
//        },
//        {
//            "enable": false,
//            "helpUrl": "http://www.xiangqu.com/agreement/guarantee#A3",
//            "icon": "http://xqres.xiangqu.com/icn_service2@2x.png",
//            "key": -1,
//            "pcIcon": "http://xqres.xiangqu.com/web_xiangqu.png",
//            "title": "担保交易"
//        }
//        ],
//        "hasActivity": null,
//        "hasFaver": false,
//        "hasFollow": false,
//        "height": 450,
//        "image": "http://xqproduct.xiangqu.com/Fg3o_L34-SUv3XXsoRdmcMhZUyYk?imageView2/2/w/450/q/90/format/jpg/640x640/",
//        "imgsUrlList": [
//        "http://xqproduct.xiangqu.com/FnBypTAP6hAAZRv5kVwNeIrqXscs?imageView2/2/w/450/q/90/format/jpg/640x640/",
//        "http://xqproduct.xiangqu.com/FmdSAiWZXZRK8F8zf8dyA6RcRe7u?imageView2/2/w/450/q/90/format/jpg/640x640/",
//        "http://xqproduct.xiangqu.com/Fprb67AXqRlpqkD2O1fncwaTv-Yu?imageView2/2/w/450/q/90/format/jpg/640x640/",
//        "http://xqproduct.xiangqu.com/FnCrkVXhV8BZqJoAqbJXTTaSGy7m?imageView2/2/w/450/q/90/format/jpg/640x640/"
//        ],
//        "isDelay": false,
//        "originalPrice": 165,
//        "parentCategoryId": 1,
//        "place": "浙江杭州",
//        "platFormWeixin": {
//        "desc": "加想去君微信：successfulsofe，成为本君好友，超多朋友福利，带你认识更多设计师哦～",
//        "iconImg": "http://xquser.xiangqu.com/FhL3_7lXAOlpibJZd9v9JIKsgDxw?imageView2/2/w/640/q/90/format/jpg/640x640/",
//        "id": "successfulsofe",
//        "img": "http://xqres.xiangqu.com/icn_wechat.png",
//        "qrcode": "http://xqres.xiangqu.com/successfulsofe.jpg",
//        "userId": "17019802"
//    },
//        "postage": "",
//        "price": 165,
//        "productActionlist": [],
//        "productDiscountTxt": "登录后，分享该商品即可获得1张满减券",
//        "productId": 3128357,
//        "productNote": null,
//        "productState": "ONSALE",
//        "productURL": "http://www.xiangqu.com/detail?id=3128357&share=true&pid=3128357",
//        "productUser": "北望",
//        "productUserDes": "设计来自于灵感",
//        "purchaseAddress": null,
//        "shareId": "17019802",
//        "shareNick": "北望",
//        "shareUserImage": "qn|xquser|FqHThWK9xsDVk6-UNETPk3oU9WY1|1080x1080",
//        "shopId": "3imxs4z6",
//        "smallImage": "http://xqproduct.xiangqu.com/Fg3o_L34-SUv3XXsoRdmcMhZUyYk?imageView2/2/w/120/q/90/format/jpg/640x640/",
//        "thirdId": "3128357",
//        "thirdUrl": null,
//        "time": "2018年12月07日",
//        "title": "北望原创 18秋冬新款 韩版休闲针织 港风 男士毛衣 ",
//        "userId": 17019802,
//        "width": 450
//    }
}