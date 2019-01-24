package com.jojo.design.module_discover.ui

import android.transition.Transition
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.jojo.design.common_base.config.arouter.ARouterConfig
import com.jojo.design.common_base.config.arouter.ARouterConstants
import com.jojo.design.common_base.dagger.mvp.BaseActivity
import com.jojo.design.common_base.dagger.mvp.BaseContract
import com.jojo.design.common_base.utils.glide.GlideUtils
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_discover.R
import com.jojo.design.module_discover.bean.ItemEntity
import com.shuyu.gsyvideoplayer.video.base.GSYVideoView
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.act_playvideo.*

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/24 7:03 PM
 *    desc   : 播放视频
 */
@Route(path = ARouterConfig.ACT_PlayVideo)
class ACT_PlayVideo : BaseActivity<BaseContract.BasePresenter, BaseContract.BaseModel>() {
    var videoBean: ItemEntity.ItemDataEntity.DataEntity.ItemBean? = null
    var playUrl: String? = null
    var playTitile: String? = null
    private var transition: Transition? = null
    companion object {
        var IMG_TRANSITION = "IMG_TRANSITION"
    }

    override fun getContentViewLayoutId(): Int = R.layout.act_playvideo

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null
    override fun getOverridePendingTransitionMode(transitionMode: TransitionMode): TransitionMode {
        return super.getOverridePendingTransitionMode(TransitionMode.BOTTOM)
    }

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
    }

    override fun startEvents() {
        playTitile = intent.extras.getString(ARouterConstants.PLAY_TITLE)
        playUrl = intent.extras.getString(ARouterConstants.PLAY_URL)
        tv_title.text = playTitile
        videoPlayer.backButton.setOnClickListener { finish() }
        initVideo()
    }


    private fun initVideo() {

        //设置加载时封面
        val ivCoverVideo = ImageView(this)
        ivCoverVideo.scaleType = ImageView.ScaleType.CENTER_CROP
        GlideUtils.loadNormalImage("", ivCoverVideo, 0)
        videoPlayer.thumbImageView = ivCoverVideo
        videoPlayer.setUp(playUrl, false, "")
        videoPlayer.startPlayLogic()

    }

    override fun onPause() {
        super.onPause()
        videoPlayer.onVideoPause()
    }

    override fun onRestart() {
        super.onRestart()
        videoPlayer.onVideoResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoView.releaseAllVideos()
    }
}