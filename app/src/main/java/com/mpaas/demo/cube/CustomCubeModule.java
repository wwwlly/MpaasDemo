package com.mpaas.demo.cube;

import androidx.annotation.NonNull;

import com.antfin.cube.antcrystal.api.CubeJSCallback;
import com.antfin.cube.antcrystal.api.CubeModule;
import com.antfin.cube.platform.api.JsMethod;
import com.mpaas.demo.utils.Logger;
import com.mpaas.demo.utils.OkhttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
            if (cubeCard != null) cubeCard.callJsFunction("clientToCube", "start request......");
//            callback.invoke("start request");
            OkhttpUtils.INSTANCE.getAsync("https://www.httpbin.org/get?a=1&b=2", new Callback() {
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    callback.invoke(response.body().string());
                }

                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    callback.invoke(e.toString());
                }
            });
        }
    }
}
