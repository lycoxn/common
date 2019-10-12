package com.lycon.common.ui

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.lycon.common.R
import kotlinx.android.synthetic.main.activity_webview.*

class WebViewActivity : AppCompatActivity() {

    private var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        webView = WebView(this)
        webView?.let {
            it.settings.domStorageEnabled = true
            it.settings.javaScriptEnabled = true
            it.settings.blockNetworkImage = false
            it.webViewClient = object : WebViewClient() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    view?.loadUrl(request?.url.toString())
                    return true
                }
            }
            web.addView(it)
            it.loadUrl("https://jdev.bhsgd.net/user/login/redirect?token=:Mjk3NDE4X0xlMmVoMDNzdldqeVo5cE1SOVFQd3l2Q0k3cGloMmVCXzE1Njg4NjM1MTA=&num=bg3")
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView?.canGoBack()!!) {
                webView?.goBack()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        webView?.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
        webView?.tag = null
        webView?.clearHistory()
        (webView?.parent as ViewGroup).removeView(webView)
        webView?.destroy()
        webView = null
        super.onDestroy()
    }

}