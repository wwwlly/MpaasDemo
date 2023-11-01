package com.mpaas.demo.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mpaas.demo.R;

import java.util.HashMap;

import io.dcloud.feature.sdk.DCUniMPSDK;
import io.dcloud.feature.sdk.Interface.IDCUniMPOnCapsuleCloseButtontCallBack;
import io.dcloud.feature.sdk.Interface.IUniMP;
import io.dcloud.feature.unimp.config.UniMPOpenConfiguration;

/**
 * uniapp 小程序
 */
public class UniAppActivity extends AppCompatActivity {

    private Context mContext;
    /**
     * unimp小程序实例缓存
     **/
    private HashMap<String, IUniMP> mUniMPCaches = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uniapp);
        mContext = this;

        //用来测试sdkDemo 胶囊×的点击事件，是否生效；lxl增加的
        DCUniMPSDK.getInstance().setCapsuleCloseButtonClickCallBack(new IDCUniMPOnCapsuleCloseButtontCallBack() {
            @Override
            public void closeButtonClicked(String appid) {
                Toast.makeText(mContext, "点击×号了", Toast.LENGTH_SHORT).show();
                if (mUniMPCaches.containsKey(appid)) {
                    IUniMP mIUniMP = mUniMPCaches.get(appid);
                    if (mIUniMP != null && mIUniMP.isRuning()) {
                        mIUniMP.closeUniMP();
                        mUniMPCaches.remove(appid);
                    }
                }
            }
        });
    }

    public void onClickDemo(View view) {
        try {
//            UniMPOpenConfiguration uniMPOpenConfiguration = new UniMPOpenConfiguration();
//            uniMPOpenConfiguration.splashClass = MySplashView.class;
//            uniMPOpenConfiguration.extraData.put("darkmode", "light");
            IUniMP uniMP = DCUniMPSDK.getInstance().openUniMP(mContext, "__UNI__378247B");
            mUniMPCaches.put(uniMP.getAppid(), uniMP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
