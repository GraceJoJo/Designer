package com.jojo.design.common_base.dagger.mvp.databinding

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import butterknife.ButterKnife
import butterknife.Unbinder
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.R
import com.jojo.design.common_base.bean.ErrorBean
import com.jojo.design.common_base.config.constants.BroadCastConstant
import com.jojo.design.common_base.dagger.mvp.BaseContract
import com.jojo.design.common_base.dagger.mvp.IBase
import com.jojo.design.common_base.utils.StatusBarHelper
import com.jojo.design.common_ui.dialog.LoadingDialog
import com.jojo.design.common_ui.view.MultipleStatusView
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/4 9:21 PM
 *    desc   : Dagger2_MVP-Activity的基类 (Activity动画、支持DataBinding、事件订阅EventBus/广播、状态栏、ButterKnife，多状态View切换)
 */
abstract class BaseDBActivity<P : BaseContract.BasePresenter, M : BaseContract.BaseModel, DB : ViewDataBinding> : AppCompatActivity(), IBase, BaseContract.BaseView {
    @Nullable
    @Inject
    @JvmField
    var mPresenter: P? = null
    @Nullable
    @Inject
    @JvmField
    var mModel: M? = null
    @Nullable
    protected var mMultipleStatusView: MultipleStatusView? = null
    protected lateinit var mContext: Context
    private lateinit var unBinder: Unbinder
    protected var viewDataBinding: DB? = null
    protected var mIsBind: Boolean = false
    protected var mTransitionMode = BaseDBActivity.TransitionMode.RIGHT
    protected var mIsRegisterReceiver = false
    lateinit var mLoadingDialog: LoadingDialog

    /**
     * OverridePendingTransition
     */
    enum class TransitionMode {
        LEFT, RIGHT, TOP, BOTTOM, SCALE, FADE, ZOOM, NOON
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        mLoadingDialog = LoadingDialog(this)

        if (getContentViewLayoutId() != 0) {
            viewDataBinding = DataBindingUtil.inflate<DB>(LayoutInflater.from(this), getContentViewLayoutId(), null, false)
            setContentView(viewDataBinding?.root)
        } else {
            throw IllegalArgumentException("You must return a right contentView layout resource Id")
        }

        //根据子类布局自定义的区域show多状态布局
        mMultipleStatusView = getLoadingMultipleStatusView()
        //如果子类返回null,则处理成showloading为整个布局范围
        if (mMultipleStatusView == null) {
            mMultipleStatusView = MultipleStatusView(this)
            viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(this), getContentViewLayoutId(), mMultipleStatusView, true)
            setContentView(mMultipleStatusView)
        }
        unBinder = ButterKnife.bind(this)

        initDaggerInject(BaseAppliction.mApplicationComponent)
        mPresenter?.attachViewModel(this, mModel!!)

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

        //事件订阅
        if (isBindEventBus(mIsBind)) {
            EventBus.getDefault().register(this)
        }
        registerBroadCastReceiver()

        //设置沉浸式状态栏
        StatusBarHelper.setStatusBar(this, false, true)

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
            intent.action = info.packageName + BroadCastConstant.BROADCASE_ADDRESS
            intent.putExtra(BroadCastConstant.BROADCASE_INTENT, value)
            context.sendBroadcast(intent)
        } catch (e: PackageManager.NameNotFoundException) {
        }

    }

    //广播接收器
    private var broadcastReceiver: BroadcastReceiver? = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            try {
                var info = packageManager.getPackageInfo(packageName, 0)

                if (intent.action == info.packageName + BroadCastConstant.BROADCASE_ADDRESS) {
                    var bundle = intent.extras
                    var i = bundle!!.getInt(BroadCastConstant.BROADCASE_INTENT)
                    if (i != null) {
                        this@BaseDBActivity.onReceiveBroadcast(i, bundle)
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
            registerReceiver(broadcastReceiver, IntentFilter(info.packageName + BroadCastConstant.BROADCASE_ADDRESS))
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
        mPresenter?.detachView()
        mPresenter?.onDestroy()

    }

    override fun showLoading() {
        mMultipleStatusView?.showLoading()
    }


    fun isBindEventBus(isBind: Boolean): Boolean {
        mIsBind = isBind
        return mIsBind
    }

    open fun getOverridePendingTransitionMode(transitionMode: BaseDBActivity.TransitionMode): BaseDBActivity.TransitionMode {
        mTransitionMode = transitionMode
        return mTransitionMode
    }

    override fun showDialogLoading(msg: String) {
        mLoadingDialog.setTitleText(msg).show()
    }

    override fun dismissDialogLoading() {
        mLoadingDialog.dismiss()
    }

    override fun showBusinessError(error: ErrorBean) {
        mMultipleStatusView?.showError()
    }

    override fun showException(error: ErrorBean) {
        mMultipleStatusView?.showNoNetwork()
    }

}