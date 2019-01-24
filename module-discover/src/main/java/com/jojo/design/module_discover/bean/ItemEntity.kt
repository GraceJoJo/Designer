package com.jojo.design.module_discover.bean

import java.io.Serializable

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/18 2:38 PM
 *    desc   : 分类详情页数据实体
 */
data class ItemEntity(var itemList: List<ItemDataEntity>, var type: String) : Serializable {
    data class ItemDataEntity(var type: String, var data: DataEntity, var tag: String, var id: Int, var adIndex: Int) {

        data class DataEntity(var dataType: String, var text: String, var header: HeadBean, var itemList: List<ItemBean>, var id: String, var title: String, var description: String, var library: String,
                              var category: String, var playUrl: String, var playInfo: List<ItemBean.DataItemBean.PlayInfoBean>, var cover: ItemBean.DataItemBean.CoverBean, var author: ItemBean.DataItemBean.AuthorBean) : Serializable {
            data class HeadBean(var id: String, var title: String, var description: String) : Serializable

            data class ItemBean(var type: String, var data: DataItemBean) : Serializable {
                data class DataItemBean(var dataType: String, var id: String, var title: String, var description: String, var library: String,
                                        var category: String, var playUrl: String, var playInfo: List<PlayInfoBean>, var cover: CoverBean, var author: AuthorBean) : Serializable {
                    data class CoverBean(var detail: String, var blurred: String) : Serializable
                    data class PlayInfoBean(var urlList: List<UrlListBean>) : Serializable {
                        data class UrlListBean(var name: String, var url: String, var size: Long) : Serializable
                    }

                    data class AuthorBean(var name: String, var description: String, var icon: String) : Serializable
                }
            }
//            ItemBean
//            {
//                "type": "video",
//                "data": {
//                "dataType": "VideoBeanForClient",
//                "id": 147934,
//                "title": "「哈斯塔的传说」",
//                "description": "哈斯塔的传说第三章！",
//                "library": "NOT_RECOMMEND",
//                "tags": [
//                {
//                    "id": 12,
//                    "name": "剧情",
//                    "actionUrl": "eyepetizer://tag/12/?title=%E5%89%A7%E6%83%85",
//                    "adTrack": null,
//                    "desc": "用一个好故事，描绘生活的不可思议",
//                    "bgPicture": "http://img.kaiyanapp.com/945fa937f0955b31224314a4eeef59b8.jpeg?imageMogr2/quality/100",
//                    "headerImage": "http://img.kaiyanapp.com/945fa937f0955b31224314a4eeef59b8.jpeg?imageMogr2/quality/100",
//                    "tagRecType": "NORMAL",
//                    "childTagList": null,
//                    "childTagIdList": null,
//                    "communityIndex": 0
//                }
//                ],
//                "consumption": {
//                "collectionCount": 0,
//                "shareCount": 0,
//                "replyCount": 0
//            },
//                "resourceType": "video",
//                "slogan": null,
//                "provider": {
//                "name": "PGC",
//                "alias": "PGC",
//                "icon": ""
//            },
//                "category": "剧情",
//                "author": {
//                "id": 3281,
//                "icon": "http://img.kaiyanapp.com/c2c46620a46b3fa8f08f947a22e86d11.png?imageMogr2/quality/60/format/jpg",
//                "name": "SquirrelSutudio",
//                "description": "一名摄影师眼中的世界\n\nSquirrel晓鬆",
//                "link": "",
//                "latestReleaseTime": 1548210249000,
//                "videoNum": 47,
//                "adTrack": null,
//                "follow": {
//                "itemType": "author",
//                "itemId": 3281,
//                "followed": false
//            },
//                "shield": {
//                "itemType": "author",
//                "itemId": 3281,
//                "shielded": false
//            },
//                "approvedNotReadyVideoCount": 0,
//                "ifPgc": true,
//                "recSort": 0,
//                "expert": false
//            },
//                "cover": {
//                "feed": "http://img.kaiyanapp.com/2623c59a474097ffcb12a42ac17e3191.png?imageMogr2/quality/60/format/jpg",
//                "detail": "http://img.kaiyanapp.com/2623c59a474097ffcb12a42ac17e3191.png?imageMogr2/quality/60/format/jpg",
//                "blurred": "http://img.kaiyanapp.com/9fda688eaa3f812904f9c59281b4afac.jpeg?imageMogr2/quality/60/format/jpg",
//                "sharing": null,
//                "homepage": null
//            },
//                "playUrl": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=147934&resourceType=video&editionType=default&source=aliyun",
//                "thumbPlayUrl": null,
//                "duration": 872,
//                "webUrl": {
//                "raw": "http://www.eyepetizer.net/detail.html?vid=147934",
//                "forWeibo": "http://www.eyepetizer.net/detail.html?vid=147934&resourceType=video&utm_campaign=routine&utm_medium=share&utm_source=weibo&uid=0"
//            },
//                "releaseTime": 1548210249000,
//                "playInfo": [
//                {
//                    "height": 480,
//                    "width": 854,
//                    "urlList": [
//                    {
//                        "name": "aliyun",
//                        "url": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=147934&resourceType=video&editionType=normal&source=aliyun",
//                        "size": 75518783
//                    },
//                    {
//                        "name": "qcloud",
//                        "url": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=147934&resourceType=video&editionType=normal&source=qcloud",
//                        "size": 75518783
//                    },
//                    {
//                        "name": "ucloud",
//                        "url": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=147934&resourceType=video&editionType=normal&source=ucloud",
//                        "size": 75518783
//                    }
//                    ],
//                    "name": "标清",
//                    "type": "normal",
//                    "url": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=147934&resourceType=video&editionType=normal&source=aliyun"
//                },
//                {
//                    "height": 720,
//                    "width": 1280,
//                    "urlList": [
//                    {
//                        "name": "aliyun",
//                        "url": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=147934&resourceType=video&editionType=high&source=aliyun",
//                        "size": 135357588
//                    },
//                    {
//                        "name": "qcloud",
//                        "url": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=147934&resourceType=video&editionType=high&source=qcloud",
//                        "size": 135357588
//                    },
//                    {
//                        "name": "ucloud",
//                        "url": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=147934&resourceType=video&editionType=high&source=ucloud",
//                        "size": 135357588
//                    }
//                    ],
//                    "name": "高清",
//                    "type": "high",
//                    "url": "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=147934&resourceType=video&editionType=high&source=aliyun"
//                }
//                ],
//                "campaign": null,
//                "waterMarks": null,
//                "ad": false,
//                "adTrack": null,
//                "type": "NORMAL",
//                "titlePgc": "「哈斯塔的传说」",
//                "descriptionPgc": "哈斯塔的传说第三章！",
//                "remark": "",
//                "ifLimitVideo": false,
//                "searchWeight": 0,
//                "idx": 0,
//                "shareAdTrack": null,
//                "favoriteAdTrack": null,
//                "webAdTrack": null,
//                "date": 1548210249000,
//                "promotion": null,
//                "label": null,
//                "labelList": [],
//                "descriptionEditor": "",
//                "collected": false,
//                "played": false,
//                "subtitles": [],
//                "lastViewTime": null,
//                "playlists": null,
//                "src": null
//            },
//                "tag": null,
//                "id": 0,
//                "adIndex": -1
//            }
        }
    }
}