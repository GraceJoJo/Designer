package com.jojo.design.common_base.config.arouter

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/24 3:44 PM
 *    desc   : base-路由页面传递参数配置
 */
interface ARouterConstants {
    companion object {
        /**
         * 想去App
         */
        val WEB_URL = "web_url"
        val WEB_TITLE = "web_title"
        val TAGCATEGORY_ID = "tagcategory_id"
        val TAG_ID = "tag_id"
        val TAG_NAME: String = "tag_name"
        val SEARCH_KEYWORDS: String = "search_keywords"
        val PRODUCT_ID: String = "product_id"
        /**
         *  开眼视频
         */
        val CATEGORY_ID: String = "category_id"
        val CATEGORY_HEAD_IMAGE: String = "category_head_image"
        val CATEGORY_NAME: String = "category_name"
        val CATEGORY_BEAN: String = "category_bean"
        val PLAY_VIDEO_BEAN: String = "play_video_bean"
        val PLAY_URL: String="play_url"
        val PLAY_TITLE: String="play_title"
        val COVER_IMG: String = "cover_img"
        val VIDEO_BG_IMG: String="video_bg_img"
    }

}