package com.jojo.design.common_base.mvvm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import butterknife.ButterKnife
import butterknife.Unbinder
import com.jojo.design.common_base.LoadingDialog
import com.jojo.design.common_base.R
import com.jojo.design.common_base.bean.ErrorBean
import com.jojo.design.common_base.constants.BroadCastConstant
import com.jojo.design.common_base.constants.BroadCastConstant.Companion.BROADCASE_ADDRESS
import com.jojo.design.common_base.constants.BroadCastConstant.Companion.BROADCASE_INTENT
import com.jojo.design.common_base.utils.ClassReflectHelper
import com.jojo.design.common_base.utils.StatusBarHelper
import com.jojo.design.common_base.view.MultipleStatusView
import org.greenrobot.eventbus.EventBus

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/2 6:46 PM
 *    desc   : Activity的基类-Kotlin
 */
open abstract class BaseActivity<P : BasePresenter<IBaseView, IBaseModel>, M : IBaseModel, DB : ViewDataBinding> : AppCompatActivity(), IBaseView {
    protected var mMvpPresenter: P? = null
    protected var mModel: M? = null

    /**
     * OverridePendingTransition
     */
    enum class TransitionMode {
        LEFT, RIGHT, TOP, BOTTOM, SCALE, FADE, ZOOM, NOON
    }

    protected lateinit var mContext: Context
    protected lateinit var viewDataBinding: DB
    lateinit var mMultipleStatusView: MultipleStatusView
    lateinit var mLoadingDialog: LoadingDialog
    private lateinit var unBinder: Unbinder
    protected var mIsBind: Boolean = false
    protected var mTransitionMode = TransitionMode.RIGHT
    protected var mIsRegisterReceiver = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        mLoadingDialog = LoadingDialog(this)
        //Activity默认动画为右进右出
        when (getOverridePendingTransitionMode(mTransitionMode)) {
            TransitionMode.LEFT -> overridePendingTransition(R.anim.left_in, R.anim.left_out)
            TransitionMode.RIGHT -> overridePendingTransition(R.anim.enter_trans, R.anim.exit_scale)
            TransitionMode.TOP -> overridePendingTransition(R.anim.top_in, R.anim.top_out)
            TransitionMode.BOTTOM -> overridePendingTransition(R.anim.bottom_in, 0)
            TransitionMode.SCALE -> overridePendingTransition(R.anim.scale_in, R.anim.scale_out)
            TransitionMode.FADE -> overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            TransitionMode.ZOOM -> overridePendingTransition(R.anim.zoomin, R.anim.zoomout)
        }

        if (getContentViewLayoutID() != 0) {
            mMultipleStatusView = MultipleStatusView(this)
            //必须指定parent为mMultipleStatusView，否则布局区域无法match
            viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(this), getContentViewLayoutID(), mMultipleStatusView, true)
//            mMultipleStatusView.addView(viewDataBinding.root)
            setContentView(mMultipleStatusView)
            unBinder = ButterKnife.bind(this, mMultipleStatusView)
            //报错：The specified child already has a parent. You must call removeView() on the child's parent first.
//            setContentView(viewDataBinding.root)
        } else {
            throw IllegalArgumentException("You must return a right contentView layout resource Id")
        }
        //事件订阅
        if (isBindEventBus(mIsBind)) {
            EventBus.getDefault().register(this)
        }
        registerBroadCastReceiver()

        //设置沉浸式状态栏
        StatusBarHelper.setStatusBar(this, false, true)
        //非全屏
//        StatusBarHelper.setStautsBarColor(this, resources.getColor(R.color.color_212121), 0)

        //MVP
        mMvpPresenter = ClassReflectHelper.getT(this, 0)
        mModel = ClassReflectHelper.getT(this, 1)
        if (this is IBaseView) {
            if (mMvpPresenter != null && mModel != null) {
                mMvpPresenter?.setVM(this, mModel!!)
            }
        }
        startEvents()
    }


    /**
     * 发送一个广播
     *
     * @param value
     */
    protected fun sendCommonBroadcast(value: Int) {
        sendBroadcast(this, value)
    }

    /**
     * 发送一个广播
     *
     * @param value
     */
    open fun sendBroadcast(context: Context, value: Int) {
        try {
            val info = context.packageManager.getPackageInfo(context.packageName, 0)
            val intent = Intent()
            intent.action = info.packageName + BROADCASE_ADDRESS
            intent.putExtra(BROADCASE_INTENT, value)
            context.sendBroadcast(intent)
        } catch (e: PackageManager.NameNotFoundException) {
        }

    }

    //广播接收器
    private var broadcastReceiver: BroadcastReceiver? = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            try {
                var info = packageManager.getPackageInfo(packageName, 0)

                if (intent.action == info.packageName + BROADCASE_ADDRESS) {
                    var bundle = intent.extras
                    var i = bundle!!.getInt(BROADCASE_INTENT)
                    if (i != null) {
                        this@BaseActivity.onReceiveBroadcast(i, bundle)
                    }
                }
            } catch (e: PackageManager.NameNotFoundException) {
            }

        }
    }

    /**
     * 注册广播
     */
    private fun registerBroadCastReceiver() {
        try {
            var info = packageManager.getPackageInfo(packageName, 0)
            registerReceiver(broadcastReceiver, IntentFilter(info.packageName + BROADCASE_ADDRESS))
            mIsRegisterReceiver = true
        } catch (e: Exception) {

        }

    }

    protected open fun onReceiveBroadcast(intent: Int, bundle: Bundle) {
        if (intent == BroadCastConstant.LOGOUT) {
            finish()
        }
    }


    override fun finish() {
        super.finish()
        when (getOverridePendingTransitionMode(mTransitionMode)) {
            TransitionMode.LEFT -> overridePendingTransition(R.anim.left_in, R.anim.left_out)
            TransitionMode.RIGHT -> overridePendingTransition(R.anim.enter_scale, R.anim.exit_trans)
            TransitionMode.TOP -> overridePendingTransition(R.anim.top_in, R.anim.top_out)
            TransitionMode.BOTTOM -> overridePendingTransition(0, R.anim.bottom_out)
            TransitionMode.SCALE -> overridePendingTransition(R.anim.scale_in, R.anim.scale_out)
            TransitionMode.FADE -> overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            TransitionMode.ZOOM -> overridePendingTransition(R.anim.zoomin, R.anim.zoomout)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unBinder.unbind()
        if (mIsBind) {
            EventBus.getDefault().unregister(this)
        }
        if (mIsRegisterReceiver && broadcastReceiver != null) {
            try {
                mIsRegisterReceiver = false
                this.unregisterReceiver(broadcastReceiver)
            } catch (e: Exception) {
            } finally {
                broadcastReceiver = null
            }
        }
        mMvpPresenter?.onDestroy()
    }

    override fun showLoading() {
        mMultipleStatusView.showLoading()
    }

    override fun showDialogLoading(msg: String) {
        mLoadingDialog.setTitleText(msg).show()
    }

    override fun dismissDialogLoading() {
        mLoadingDialog.dismiss()
    }

    override fun showBusinessError(error: ErrorBean) {
        mMultipleStatusView.showError()
    }

    override fun showException(error: ErrorBean) {
        mMultipleStatusView.showNoNetwork()
    }

    fun isBindEventBus(isBind: Boolean): Boolean {
        mIsBind = isBind
        return mIsBind
    }

    open fun getOverridePendingTransitionMode(transitionMode: TransitionMode): TransitionMode {
        mTransitionMode = transitionMode
        return mTransitionMode
    }

    abstract fun getContentViewLayoutID(): Int
    abstract fun startEvents()

}