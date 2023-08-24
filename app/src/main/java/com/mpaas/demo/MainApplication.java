package com.mpaas.demo;

import android.app.ActivityManager;
import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.alipay.mobile.antcube.CubeService;
import com.alipay.mobile.cubedebug.crystal.CubeCardDebug;
import com.alipay.mobile.framework.quinoxless.IInitCallback;
import com.alipay.mobile.framework.quinoxless.QuinoxlessFramework;
import com.antfin.cube.antcrystal.api.CExceptionInfo;
import com.antfin.cube.antcrystal.api.CExceptionListener;
import com.antfin.cube.antcrystal.api.CubeEngineConfig;
import com.antfin.cube.antcrystal.api.CubeModuleModel;
import com.antfin.cube.antcrystal.api.CubeWidgetInfo;
import com.mpaas.core.MP;
import com.mpaas.core.MPInitParam;
import com.mpaas.demo.cube.CustomCubeModule;
import com.mpaas.demo.cube.CustomCubeWidget;

import java.util.Collection;
import java.util.LinkedList;

public class MainApplication extends MultiDexApplication {

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
        MP.init(this, MPInitParam.obtain().setCallback(new MPInitParam.MPCallback() {
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
            }
        }));

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
        cubeModuleModels.add(new CubeModuleModel("custom", CustomCubeModule.class.getName(), new String[]{"cubeToClient"}));
        CubeService.instance().getEngine().registerModule(cubeModuleModels, null);
    }

    private void initWidget() {
        Collection<CubeWidgetInfo> widgetInfos = new LinkedList<>();
        widgetInfos.add(new CubeWidgetInfo("custom-widget", CustomCubeWidget.class.getName()));
        CubeService.instance().getEngine().registerWidgets(widgetInfos);
    }
}
