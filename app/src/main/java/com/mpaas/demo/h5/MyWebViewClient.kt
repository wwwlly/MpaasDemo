package com.mpaas.demo.h5

import android.annotation.SuppressLint
import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.mpaas.demo.utils.Logger

class MyWebViewClient : WebViewClient() {

    companion object {
        const val TAG = "MyWebViewClient"
    }

    override fun shouldOverrideUrlLoading(
        webView: WebView?,
        request: WebResourceRequest?
    ): Boolean {
        val url = request?.url?.toString() ?: ""
        val scheme = request?.url?.scheme ?: ""
        Logger.d(TAG, "shouldOverrideUrlLoading url: $url \n scheme: $scheme")

        return if (scheme == "http" || scheme == "https") {
            webView?.loadUrl(url)
            true
        } else {
            Logger.d(TAG, "scheme: $scheme")
            true
        }
    }

    @SuppressLint("WebViewClientOnReceivedSslError")
    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        handler?.proceed()
    }
}