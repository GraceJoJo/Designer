package com.jojo.design.module_core.bean

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/12 2:04 PM
 *    desc   : 设计师详情
 */
data class DesignerDetailEntity(val shopId: String, val shareUrl: String, val userNick: String, val userAvatar: String, val intro: String, val items: List<ItemBean>, val products: List<ProductBean>) {
    //ItemBean 详情内容：文本和图片组成 type 1:文本 2：图片
    data class ItemBean(val content: String, val type: Int, val height: Int, val width: Int)
    //设计师的相关产品
    data class ProductBean(val image: String, val title: String)
    {
//        "banner": "http://xqtopic.xiangqu.com/Fle0UA93CzKQCwKOvte-F0LOSR85?imageView2/2/w/640/q/90/format/jpg/960x645/",
//        "fansNum": null,
//        "id": 109,
//        "intro": "郑呱呱2012年毕业于华南农业大学艺术学院，每天幻想，昼伏夜出，爱好太多，以致生活一塌糊涂，每天弹琴、画画、做梦，执迷乌托邦却忽略了生活。\r\n三年职业生涯历经雕塑设计、西点设计，虽得心应手，却从不能真心喜悦，当所有年少时的梦想都照进现实，被大太阳烘干碾碎后，我觉得，愉悦人心，要先从愉悦己心开始，2015年8月，郑呱呱终于诞生了。",
//        "isFollow": false,
//        "items": [
//        {
//            "backgroundImage": null,
//            "content": "小的时候，家里院子旁边有一个很大的池子，每到傍晚，一池的蛙叫，小时候是一段时间总是很长的时光，那个院子，还有院子旁边的池塘，就是我所有的童年，那一池蛙叫伴随了我整个童年，所以决定做品牌的时候郑呱呱也就应运而生，那一池蛙叫，它总是给我力量，也是我童年的美好回忆。",
//            "height": null,
//            "type": 1,
//            "width": null
//        }
//        ],
//        "shareUrl": "http://www.xiangqu.com/designer2/index?id=109",
//        "shopId": "1rhydr95",
//        "shopName": "郑呱呱原创设计",
//        "tags": [
//        {
//            "categoryId": 1,
//            "id": 3,
//            "image": "http://xqtopic.xiangqu.com/Ftg3BELXpvaRtTR30nJz5DXYKf7_?imageView2/2/w/320/q/90/format/jpg/320x320/",
//            "name": "配饰"
//        },
//        {
//            "categoryId": 2,
//            "id": 7,
//            "image": "http://xqtopic.xiangqu.com/FufKBQTKN9sJhmPi5Y6JQbaAqIs2?imageView2/2/w/320/q/90/format/jpg/480x480/",
//            "name": "简约"
//        }
//        ],
//        "userAvatar": "http://xquser.xiangqu.com/Fss0IuQwM5kuWDapKOV6_wlU9c0W?imageView2/2/w/120/q/90/format/jpg/645x645/",
//        "userId": 578142,
//        "userNick": "郑呱呱"
    }
}