package com.jojo.design.module_discover.adapter

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.alibaba.android.arouter.launcher.ARouter
import com.jojo.design.common_base.adapter.rv.ItemViewDelegate
import com.jojo.design.common_base.adapter.rv.ViewHolder
import com.jojo.design.common_base.config.arouter.ARouterConfig
import com.jojo.design.common_base.config.arouter.ARouterConstants
import com.jojo.design.common_base.utils.DataFormatUtils
import com.jojo.design.common_base.utils.glide.GlideUtils
import com.jojo.design.module_discover.R
import com.jojo.design.module_discover.bean.ItemEntity

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/24 3:55 PM
 *    desc   : type=video
 */
class VideoViewType constructor(context: Context) : ItemViewDelegate<ItemEntity.ItemDataEntity> {
    override fun getItemViewLayoutId(): Int = R.layout.item_video_type

    override fun isForViewType(item: ItemEntity.ItemDataEntity, position: Int): Boolean = item.type == "video"

    override fun convert(holder: ViewHolder, bean: ItemEntity.ItemDataEntity, position: Int) {
        GlideUtils.loadNormalImage(bean?.data?.cover?.detail, holder.getView<ImageView>(R.id.iv_card), 0)
        if (bean.data.playInfo != null && bean.data.playInfo.isNotEmpty()) {
            holder.setText(R.id.tv_size, DataFormatUtils.formatTime((bean?.data?.playInfo[0]?.urlList[0]?.size) / 100))
        }
        if (bean.data.author != null) {
            GlideUtils.loadNormalCircleImage(bean?.data?.author?.icon, holder.getView<ImageView>(R.id.iv_author_icon), 0)
            holder.setText(R.id.tv_name, bean?.data?.author?.name)
            holder.setText(R.id.tv_description, "发布：" + bean?.data?.author?.description)
        }
        holder.setText(R.id.tv_des, bean?.data?.description)
        holder.setOnClickListener(R.id.iv_card, {
            ARouter.getInstance().build(ARouterConfig.ACT_PlayVideo)
                    .withString(ARouterConstants.PLAY_URL, bean.data.playUrl)
                    .withString(ARouterConstants.PLAY_TITLE, bean.data.title)
                    .withString(ARouterConstants.COVER_IMG, bean.data.cover.detail)
                    .withString(ARouterConstants.VIDEO_BG_IMG, bean.data.cover.blurred)
                    .navigation()
        })
    }

}