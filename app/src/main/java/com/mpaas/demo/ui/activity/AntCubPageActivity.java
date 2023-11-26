package com.mpaas.demo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alipay.mobile.antcube.CubeService;
import com.antfin.cube.antcrystal.api.CCardCallback;
import com.antfin.cube.antcrystal.api.CCardType;
import com.antfin.cube.antcrystal.api.CubeCard;
import com.antfin.cube.antcrystal.api.CubeCardConfig;
import com.antfin.cube.antcrystal.api.CubeCardResultCode;
import com.antfin.cube.antcrystal.api.CubeView;
import com.antfin.cube.platform.systeminfo.MFSystemInfo;
import com.mpaas.demo.R;
import com.mpaas.mas.adapter.api.MPLogger;

public class AntCubPageActivity extends AppCompatActivity {

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, AntCubPageActivity.class);
        activity.startActivity(intent);
    }

    private LinearLayout llRoot;

    private CubeCard mCard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ant_cube_page);

        llRoot = findViewById(R.id.ll_root);

        initCube();
    }

    private void initCube() {
        // 创建卡片配置
        CubeCardConfig cardConfig = new CubeCardConfig();
        // 后台创建的卡片 ID
        cardConfig.setTemplateId("ant-cube-demo");
        // 卡片版本
        cardConfig.setVersion("0.0.0.2");
        // 卡片宽度，这里选取屏幕宽度
        cardConfig.setWidth(MFSystemInfo.getPortraitScreenWidth());
        // 卡片数据（用于渲染卡片的数据，一般是 mock.json 里面的内容）
//        JSONObject obj = new JSONObject("xxxxx");
//        cardConfig.setData(obj);
        // 创建卡片信息
        CubeService.instance().getEngine().createCard(cardConfig, new CCardCallback() {
            @Override
            public void onLoaded(CubeCard cubeCard, CCardType cardType, CubeCardConfig cubeCardConfig, CubeCardResultCode resultCode) {
                mCard = cubeCard;
                if (resultCode == CubeCardResultCode.CubeCardResultSucc) {
                    // 需要运行在主线程
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // 创建卡片 View
                            CubeView view = CubeService.instance().getEngine().createView(AntCubPageActivity.this);
                            // 添加到外层 ViewGroup 里
                            llRoot.addView(view);
                            // 渲染卡片
                            cubeCard.renderView(view);
                        }
                    });
                } else {
                    MPLogger.info("cube", "fail " + cubeCardConfig.getTemplateId() + " style " + cardType + " error " + resultCode);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCard != null) {
            mCard.recycle();
        }
        int chidrenCount = llRoot.getChildCount();
        for (int i = 0; i < chidrenCount; i++) {
            if (llRoot.getChildAt(i) instanceof CubeView) {
                ((CubeView) llRoot.getChildAt(i)).destroy();
            }
        }
        llRoot.removeAllViews();
    }
}
