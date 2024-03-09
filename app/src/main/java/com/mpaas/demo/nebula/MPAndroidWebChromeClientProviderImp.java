package com.mpaas.demo.nebula;

import android.webkit.PermissionRequest;

import com.alipay.mobile.nebula.webview.APWebChromeClient;
import com.alipay.mobile.nebula.webview.APWebView;
import com.alipay.mobile.nebulacore.android.AndroidWebChromeClient;
import com.alipay.mobile.nebulacore.android.MPAndroidWebChromeClientProvider;

public class MPAndroidWebChromeClientProviderImp implements MPAndroidWebChromeClientProvider {
    @Override
    public AndroidWebChromeClient generateAndroidWebChromeClient(APWebView apWebView, APWebChromeClient apWebChromeClient) {
        return new AndroidWebChromeClient(apWebView, apWebChromeClient) {
            @Override
            public void onPermissionRequest(PermissionRequest request) {
                super.onPermissionRequest(request);
            }

            @Override
            public void onPermissionRequestCanceled(PermissionRequest request) {
                super.onPermissionRequestCanceled(request);
            }
        };
    }
}
