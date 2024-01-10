package com.mpaas.demo;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.alipay.mobile.antcube.CubeService;
import com.alipay.mobile.cubedebug.crystal.CubeCardDebug;
import com.alipay.mobile.framework.quinoxless.IInitCallback;
import com.alipay.mobile.framework.quinoxless.QuinoxlessFramework;
import com.alipay.mobile.nebula.provider.H5AppCenterPresetProvider;
import com.alipay.mobile.nebula.util.H5Utils;
import com.antfin.cube.antcrystal.api.CExceptionInfo;
import com.antfin.cube.antcrystal.api.CExceptionListener;
import com.antfin.cube.antcrystal.api.CubeEngineConfig;
import com.antfin.cube.antcrystal.api.CubeModuleModel;
import com.antfin.cube.antcrystal.api.CubeWidgetInfo;
import com.mpaas.core.MP;
import com.mpaas.core.MPInitParam;
import com.mpaas.demo.cube.CustomCubeModule;
import com.mpaas.demo.cube.CustomCubeWidget;
import com.mpaas.mas.adapter.api.MPLogger;
import com.mpaas.mps.adapter.api.MPPush;
import com.mpaas.mriver.api.init.MriverInitParam;
import com.mpaas.tinyappcommonres.TinyAppCenterPresetProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import io.dcloud.feature.sdk.DCSDKInitConfig;
import io.dcloud.feature.sdk.DCUniMPSDK;
import io.dcloud.feature.sdk.Interface.IDCUniMPPreInitCallback;
import io.dcloud.feature.sdk.MenuActionSheetItem;

public class MainApplication extends MultiDexApplication {

    public static final String USER_ID = "id_mpaas";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // mPaaS 初始化回调设置。
        /*QuinoxlessFramework.setup(this, new IInitCallback() {
            @Override
            public void onPostInit() {
                // 此回调表示 mPaaS 已经初始化完成，mPaaS 相关调用可在这个回调里进行。
            }
        });*/
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // mPaaS 初始化。
//        QuinoxlessFramework.init();
        MP.init(this, MPInitParam.obtain().addComponentInitParam(new MriverInitParam().setMriverInitCallback(new MriverInitParam.MriverInitCallback() {
            @Override
            public void onInit() {
                H5Utils.setProvider(H5AppCenterPresetProvider.class.getName(),new TinyAppCenterPresetProvider());
            }

            @Override
            public void onError(Exception e) {

            }
        })).setCallback(new MPInitParam.MPCallback() {
            @Override
            public void onInit() {
                boolean isMainProcess = getApplicationContext().getPackageName().equals(getCurrentProcessName());
                if (isMainProcess) {
                    //初始化魔方卡片
                    CubeService.instance().initEngine(generateCubeEngineConfig(), MainApplication.this);
                    //初始化魔方卡片调试
                    CubeCardDebug.init(MainApplication.this);
                    //注册卡片to客户端通道
                    initModuleModel();
                    //注册自定义标签
                    initWidget();
                }

                MPPush.init(MainApplication.this);
                MPLogger.setUserId(USER_ID);
            }
        }));

        initUniApp();

        MultiDex.install(this);
    }

    /**
     * 获取当前进程名
     */
    private String getCurrentProcessName() {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                processName = process.processName;
            }
        }

        return processName;
    }

    private CubeEngineConfig generateCubeEngineConfig() {
        CubeEngineConfig config = new CubeEngineConfig();
        config.setResourcePath("cube");
        config.setExceptionListener(new CExceptionListener() {
            @Override
            public void onException(CExceptionInfo cExceptionInfo) {

            }
        });
//        config.setImageHandler();
        return config;
    }

    private void initModuleModel() {
        Collection<CubeModuleModel> cubeModuleModels = new LinkedList<>();
        cubeModuleModels.add(new CubeModuleModel("custom", CustomCubeModule.class.getName(), new String[]{"cubeToClient", "httpRequest"}));
        CubeService.instance().getEngine().registerModule(cubeModuleModels, null);
    }

    private void initWidget() {
        Collection<CubeWidgetInfo> widgetInfos = new LinkedList<>();
        widgetInfos.add(new CubeWidgetInfo("custom-widget", CustomCubeWidget.class.getName()));
        CubeService.instance().getEngine().registerWidgets(widgetInfos);
    }

    private void initUniApp() {
        //初始化 uni小程序SDK ----start----------
        MenuActionSheetItem item = new MenuActionSheetItem("关于", "gy");

        MenuActionSheetItem item1 = new MenuActionSheetItem("获取当前页面url", "hqdqym");
        MenuActionSheetItem item2 = new MenuActionSheetItem("跳转到宿主原生测试页面", "gotoTestPage");
        List<MenuActionSheetItem> sheetItems = new ArrayList<>();
        sheetItems.add(item);
        sheetItems.add(item1);
        sheetItems.add(item2);
        Log.i("unimp", "onCreate----");
        DCSDKInitConfig config = new DCSDKInitConfig.Builder()
                .setCapsule(false)
                .setMenuDefFontSize("16px")
                .setMenuDefFontColor("#ff00ff")
                .setMenuDefFontWeight("normal")
                .setMenuActionSheetItems(sheetItems)
                .setEnableBackground(true)//开启后台运行
                .setUniMPFromRecents(false)
                .build();
        DCUniMPSDK.getInstance().initialize(this, config, new IDCUniMPPreInitCallback() {
            @Override
            public void onInitFinished(boolean b) {
                Log.d("unimpaa", "onInitFinished----" + b);
            }
        });
        //初始化 uni小程序SDK ----end----------
    }
}
