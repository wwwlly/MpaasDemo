package com.mpaas.demo.nebula;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.alipay.mobile.framework.LauncherApplicationAgent;
import com.alipay.mobile.h5container.service.H5Service;
import com.alipay.mobile.nebula.provider.H5ViewProvider;
import com.alipay.mobile.nebula.util.H5Utils;
import com.alipay.mobile.nebulacore.android.MPAndroidWebChromeClientProvider;
import com.mpaas.nebula.adapter.api.MPNebula;
import com.mpaas.nebula.adapter.api.MpaasNebulaUpdateCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * nebual容器 api
 */
public class NebulaApi {

    private static NebulaApi INSTANCE;

    private Context context;
    private Activity activity;

    private NebulaApi(Context context) {
        this.context = context;
        if (context instanceof Activity) {
            activity = (Activity) context;
        }
    }

    public static NebulaApi getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new NebulaApi(context);
        }
        return INSTANCE;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public static void startApp(String appId) {
        MPNebula.startApp(appId);
    }

    /**
     * 启动离线包或小程序（旧容器）
     *
     * @param appId  离线包或小程序（旧容器）id
     * @param params
     */
    public static void startApp(String appId, Bundle params) {
        MPNebula.startApp(appId, params);
    }

    public static void startUrl(String appId) {
        MPNebula.startUrl(appId);
    }

    public static void startUrl(String appId, Bundle params) {
        MPNebula.startUrl(appId, params);
    }

    /**
     * 更新所有离线包
     *
     * @param callback
     */
    public static void updateAllApp(MpaasNebulaUpdateCallback callback) {
        MPNebula.updateAllApp(callback);
    }

    /**
     * 更新指定离线包
     *
     * @param appId
     * @param callback
     */
    public static void updateApp(String appId, MpaasNebulaUpdateCallback callback) {
        Map<String, String> appMap = new HashMap<>();
        //key为appid,value为version，version可以通过H5AppProvider
        appMap.put(appId, "");
        MPNebula.updateApp(appMap, callback, false);
    }

    public static H5Service getH5Service() {
        H5Service h5Service = (H5Service) LauncherApplicationAgent.getInstance().getMicroApplicationContext().findServiceByInterface(H5Service.class.getName());
        return h5Service;
    }

    /**
     * 设置Android 系统内核
     */
    public static void setMPAndroidWebChromeClient() {
        H5Utils.setProvider(MPAndroidWebChromeClientProvider.class.getName(), new MPAndroidWebChromeClientProviderImp());
    }

    /**
     * 设置h5自定义view
     */
    public static void setCustomView() {
        H5Utils.setProvider(H5ViewProvider.class.getName(), new H5ViewProviderImpl());
    }
}
