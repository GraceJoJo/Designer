package com.jojo.design.module_core.ui.common

import android.net.http.SslError
import android.os.Build
import android.webkit.*
import com.alibaba.android.arouter.facade.annotation.Route
import com.jojo.design.common_base.config.arouter.ARouterConfig
import com.jojo.design.common_base.config.arouter.ARouterConstants
import com.jojo.design.common_base.dagger.mvp.BaseActivity
import com.jojo.design.common_base.dagger.mvp.BaseContract
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_core.R
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.act_common_webview.*

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/25 11:32 AM
 *    desc   : 公共的加载Url的WebView
 */
@Route(path = ARouterConfig.ACT_WEBVIEW)
class ACT_CommonWebView : BaseActivity<BaseContract.BasePresenter, BaseContract.BaseModel>() {
    private var webUrl: String? = null
    override fun getContentViewLayoutId(): Int = R.layout.act_common_webview

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
    }

    override fun startEvents() {
        intent.extras?.getString(ARouterConstants.WEB_TITLE)?.let { setHeaderTitle(it) }
        webUrl = intent.extras?.getString(ARouterConstants.WEB_URL)
        initWebView()
    }

    private fun initWebView() {
        webview.loadUrl(webUrl)
        webview.getSettings().javaScriptEnabled = true
        webview.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webview.settings.domStorageEnabled = true
        webview.webChromeClient = WebChromeClient()
        //设置加载网页时的进度
        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (newProgress == 100) {
                    dismissDialogLoading()
                } else {
                    showDialogLoading("")
                }

            }
        }
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            // (1)解决android 6.0 webview加载https出现空白页问题
            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                handler.proceed() // 接受所有网站的证书
            }
        }
        /**
         *  (1)解决android 6.0 webview加载https出现空白页问题
         *  Webview在安卓5.0之前默认允许其加载混合网络协议内容
         *  在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
    }
}