package com.mpaas.demo.cube;

import com.antfin.cube.antcrystal.api.CubeJSCallback;
import com.antfin.cube.antcrystal.api.CubeModule;
import com.antfin.cube.platform.api.JsMethod;
import com.mpaas.demo.utils.Logger;

public class CustomCubeModule extends CubeModule {
    private static final String TAG = CustomCubeModule.class.getSimpleName();

    // 注解，uiThread 表示是否在主进程回调
    @JsMethod(uiThread = true)
    public void cubeToClient(final CubeJSCallback callback) {
        // 向卡片发送回调
        if (callback != null) {
            callback.invoke("cubeToClient callback data: " + System.currentTimeMillis());
        }
    }

    @JsMethod(uiThread = true)
    public void httpRequest(final CubeJSCallback callback) {
        Logger.d(TAG, "httpRequest");
        // 向卡片发送回调
        if (callback != null) {
            callback.invoke("httpRequest callback data: success");
        }
    }
}
