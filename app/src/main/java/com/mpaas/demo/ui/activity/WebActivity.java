package com.mpaas.demo.ui.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mpaas.demo.R;
import com.mpaas.demo.h5.MyWebChromeClient;
import com.mpaas.demo.h5.MyWebViewClient;

public class WebActivity extends AppCompatActivity {

    private WebView webView;

    private boolean isLocal = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = findViewById(R.id.webView);

        initWebView();

        String url = getIntent().getStringExtra("url");
        isLocal = getIntent().getBooleanExtra("isLocal", false);
        //"https://www.baidu.com"
        if (isLocal) {
            webView.loadUrl("file:///android_asset/h5/test.html");
        } else {
            webView.loadUrl(url);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setGeolocationEnabled(true);
        settings.setSavePassword(true);
        settings.setSaveFormData(true);
        settings.setSupportZoom(false);
        settings.setTextZoom(100);//添加了之后手机改变字体大小不会影响H5显示
        settings.setAllowFileAccess(false);// 需要使用 file 协议
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        //webview在安卓5.0之前默认允许其加载混合网络协议内容,在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            settings.setMediaPlaybackRequiresUserGesture(false);//设置允许自动播放音视频
        }

        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient());
    }
}
