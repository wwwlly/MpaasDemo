package com.mpaas.demo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alipay.mobile.h5container.api.H5Param;
import com.mpaas.demo.R;
import com.mpaas.demo.utils.Logger;
import com.mpaas.nebula.adapter.api.MPNebula;

public class NebulaActivity extends AppCompatActivity {

    private String url;

    private EditText editText;

    public static void startActivity(Activity activity, String url) {
        Intent intent = new Intent(activity, NebulaActivity.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nebula);

        url = getIntent().getStringExtra("url");
        if (TextUtils.isEmpty(url)) {
            Logger.d("url is empty");
            finish();
            return;
        }

        Logger.d("url : " + url);

        editText = findViewById(R.id.editText);
        editText.setText(url);
    }

    public void onClickNebulaUC(View view) {
        MPNebula.startUrl(editText.getText().toString());
    }

    public void onClickNebulaChrome(View view) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(H5Param.USE_SYS_WEBVIEW, false);
        MPNebula.startUrl(editText.getText().toString(), bundle);
    }

    public void onClickWebView(View view) {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url", editText.getText().toString());
        startActivity(intent);
    }
}
