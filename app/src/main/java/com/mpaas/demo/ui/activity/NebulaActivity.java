package com.mpaas.demo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alipay.mobile.h5container.api.H5Param;
import com.mpaas.demo.R;
import com.mpaas.demo.utils.Logger;
import com.mpaas.nebula.adapter.api.MPNebula;

public class NebulaActivity extends AppCompatActivity {

    public static final String TAG = "Nebula";
    private static final int TYPE_APP_ID = 0;
    private static final int TYPE_URL = 1;

    private EditText editText;
    private RadioGroup radioGroup;

    private int type = TYPE_APP_ID;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, NebulaActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nebula);

        editText = findViewById(R.id.editText);
        radioGroup = findViewById(R.id.radio_group);

        initViews();
    }

    /**
     * 77700002
     * https://tech.antfin.com
     * https://mcube.mpaas.accelerate.aliyuncs.com/ALIPUBA1220A9191115-default/77700002/0.0.0.2_all/nebula/fallback/www/index.html
     */
    private void initViews() {
        editText.setText("https://www.baidu.com");

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn_id:
                        type = TYPE_APP_ID;
                        break;
                    case R.id.btn_url:
                        type = TYPE_URL;
                        break;
                }

                Logger.d("type: " + type);
            }
        });
    }

    private void nebulaStart(int type, String text, Bundle bundle) {
        if (bundle == null) bundle = new Bundle();
        switch (type) {
            case TYPE_APP_ID:
                Logger.d(TAG, "startApp appId: " + text);
                MPNebula.startApp(text, bundle);
                break;
            case TYPE_URL:
                Logger.d(TAG, "startUrl url: " + text);
                MPNebula.startUrl(text, bundle);
                break;
        }
    }

    public void onClickNebulaUC(View view) {
        String text = editText.getText().toString();
        if (TextUtils.isEmpty(text)) {
            Logger.d("editText is empty");
            return;
        }

        nebulaStart(type, text, null);
    }

    public void onClickNebulaChrome(View view) {
        String text = editText.getText().toString();
        if (TextUtils.isEmpty(text)) {
            Logger.d("editText is empty");
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putBoolean(H5Param.USE_SYS_WEBVIEW, false);
        nebulaStart(type, text, bundle);
    }

    public void onClickWebView(View view) {
        String text = editText.getText().toString();
        if (TextUtils.isEmpty(text)) {
            Logger.d("editText is empty");
            return;
        }

        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url", text);
        startActivity(intent);
    }
}
