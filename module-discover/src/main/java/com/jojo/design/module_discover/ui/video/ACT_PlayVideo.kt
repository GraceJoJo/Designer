package com.jojo.design.module_discover.ui.video

import android.content.res.Configuration
import android.view.View
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
import com.shuyu.gsyvideoplayer.utils.GSYVideoType
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.act_playvideo.*

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/24 7:03 PM
 *    desc   : 视频播放
 */
@Route(path = ARouterConfig.ACT_PlayVideo)
class ACT_PlayVideo : BaseActivity<BaseContract.BasePresenter, BaseContract.BaseModel>() {
    var videoBean: ItemEntity.ItemDataEntity.DataEntity.ItemBean? = null
    var playUrl: String? = null
    var playTitile: String? = null
    var coverImg: String? = null
    var bgImg: String? = null
    var orientationUtils: OrientationUtils? = null
    var isPlay: Boolean = false
    var isPause: Boolean = false
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
        coverImg = intent.extras.getString(ARouterConstants.COVER_IMG)
        bgImg = intent.extras.getString(ARouterConstants.VIDEO_BG_IMG)
        tv_title.text = playTitile
        //加载视频背景图片
//        GlideUtils.loadBackgroudView(mContext, bgImg!!, layout_root)

        initVideo()
    }

    /**
     * 初始化视频播放控件
     */
    private fun initVideo() {
        //设置加载时封面
        val ivCoverVideo = ImageView(this)
        ivCoverVideo.scaleType = ImageView.ScaleType.CENTER_CROP
        videoPlayer.thumbImageView = ivCoverVideo
        GlideUtils.loadNormalImage(coverImg!!, ivCoverVideo, 0)
        videoPlayer.setUp(playUrl, false, "")

        //设置显示比例:全局的，所以页面显示比例不一样时，需重新设置
        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_DEFAULT)
        //增加title
        videoPlayer.titleTextView.visibility = View.VISIBLE
        //设置返回键
        videoPlayer.backButton.visibility = View.VISIBLE
        //设置旋转
        orientationUtils = OrientationUtils(this, videoPlayer)
        //初始化不打开外部的旋转
        orientationUtils!!.isEnable = false
        videoPlayer.isShowFullAnimation = false
        videoPlayer.isNeedLockFull = true
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true)

        //视频播放相关操作事件监听
        initVideoListener()

        //打开页面即开始播放
        videoPlayer.startPlayLogic()
    }

    /**
     * 视频播放相关操作事件监听
     */
    private fun initVideoListener() {
        //设置返回按键功能
        videoPlayer.backButton.setOnClickListener { v -> onBackPressed() }
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.fullscreenButton.setOnClickListener { v -> orientationUtils?.resolveByClick() }

        videoPlayer.setLockClickListener({ view, lock ->
            if (orientationUtils != null) {
                //配合下方的onConfigurationChanged
                orientationUtils!!.isEnable = !lock
            }
        })
        videoPlayer.setStandardVideoAllCallBack(object : SampleListener() {
            override fun onPrepared(url: String, vararg objects: Any) {
                super.onPrepared(url, objects)
                //开始播放了才能旋转和全屏
                orientationUtils!!.isEnable = true
                isPlay = true
            }

            //点击播放按钮
            override fun onClickStartIcon(url: String?, vararg objects: Any?) {
                super.onClickStartIcon(url, *objects)
            }

            override fun onAutoComplete(url: String, vararg objects: Any) {
                super.onAutoComplete(url, objects)
            }

            override fun onClickStartError(url: String, vararg objects: Any) {
                super.onClickStartError(url, objects)
            }

            override fun onQuitFullscreen(url: String, vararg objects: Any) {
                super.onQuitFullscreen(url, objects)
                if (orientationUtils != null) {
                    orientationUtils!!.backToProtVideo()
                }
            }
        })

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            videoPlayer.onConfigurationChanged(this, newConfig, orientationUtils!!)
        }
    }

    override fun onBackPressed() {

        if (orientationUtils != null) {
            orientationUtils!!.backToProtVideo()
        }

        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return
        }
        //释放所有
        videoPlayer.setStandardVideoAllCallBack(null)
        GSYVideoPlayer.releaseAllVideos()
        super.onBackPressed()
    }

    override fun onPause() {
        getCurPlay().onVideoPause()
        super.onPause()
        isPause = true
    }


    override fun onResume() {
        getCurPlay().onVideoResume()
        super.onResume()
        isPause = false
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isPlay) {
            getCurPlay().release()
        }
        if (orientationUtils != null) {
            orientationUtils?.releaseListener()
        }
    }

    private fun getCurPlay(): GSYVideoPlayer {
        return if (videoPlayer.fullWindowPlayer != null) {
            videoPlayer.fullWindowPlayer
        } else videoPlayer
    }
}